# Design a Chat System (like WhatsApp)

Notes on a 1-on-1 + group messaging app. Real-time is the key word here.

## Core features
- 1-on-1 messaging, delivered in real time.
- Online / last-seen status.
- Delivery + read receipts (sent / delivered / read — the ticks).
- Group chat.
- Message history.

## The real-time part
Normal HTTP request/response doesn't cut it — the server needs to PUSH messages to
you the moment they arrive. Options:
- **WebSockets** — a persistent two-way connection between client and server. This
  is the standard choice. Connection stays open, server can push anytime.
- (Older alternatives: long polling, but WebSockets win.)

So each online user holds an open WebSocket to a chat server.

## Sending a message (1-on-1)
1. User A sends message over their WebSocket to the chat server.
2. Server stores it (so it survives if B is offline) and figures out which server
   B is connected to.
3. If B is online → push it down B's WebSocket instantly.
4. If B is offline → keep it stored; deliver when B reconnects. Maybe a push
   notification.

## Who's connected where?
With many chat servers, you need to know which server holds which user's
connection. Keep a **presence/registry** (e.g. Redis): `user_id -> server_id`.
Servers talk to each other (or via a message queue) to route a message to the
right server.

## Data model (rough)
```
Messages(msg_id, chat_id, sender_id, text, created_at, status)
Chats(chat_id, type [1on1/group], members)
Presence: user_id -> online? + last_seen   (Redis, ephemeral)
```
Messages are write-heavy and time-ordered → a wide-column store like Cassandra
fits well.

## Group chat
Basically fan-out: when someone posts to a group, deliver to every online member's
connection and store for the offline ones. Big groups = same celebrity-style
problem as the Twitter case.

## Status / receipts
- **Online status** — based on whether the WebSocket is alive + heartbeats.
- **Delivered** — server confirms the message reached the recipient's device.
- **Read** — recipient's app tells the server "user opened it".

## Scaling
- Lots of **chat servers** behind a load balancer that supports sticky WebSocket
  connections.
- **Message queue** (Kafka) between servers for routing + reliability.
- **Sharding** messages by chat_id.
- Notification service for offline users (APNs / FCM).

## Gotchas
- Ordering of messages (use timestamps + sequence numbers).
- Exactly-once vs at-least-once delivery (handle dupes → idempotency).
- Reconnect logic: client drops, reconnects, must sync missed messages.
