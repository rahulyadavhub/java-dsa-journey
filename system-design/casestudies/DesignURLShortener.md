# Design a URL Shortener (like bit.ly)

My notes working through this one. It's the classic starter system design.

## What it needs to do
- Take a long URL, give back a short one (e.g. `short.ly/abc123`).
- Visiting the short URL redirects to the original.
- (Optional) click counts, expiry, custom aliases.

## Rough scale guess
- Way more reads (redirects) than writes (creating links). Like 100:1. So optimize
  for fast reads.

## The core trick: generating the short code
A short code is just a unique ID encoded into something short.
- Keep an auto-incrementing counter (1, 2, 3, ...).
- **Base62 encode** that number (0-9, a-z, A-Z = 62 chars). 7 base62 chars gives
  ~62^7 ≈ 3.5 trillion URLs. plenty.
- So `id=125` → encode → "cb" or whatever. short and unique because the id is unique.

(Alternative: hash the long URL with MD5 and take the first few chars, but then you
have to handle collisions. The counter approach avoids that.)

## Data model
Simple key-value fits great:
```
short_code (PK)  ->  long_url, created_at, expiry, clicks
```
Key-value store (DynamoDB / Redis) or a SQL table with an index on short_code.

## Flow
**Create:**
1. client sends long URL
2. get next id from counter → base62 encode → short_code
3. store short_code → long_url
4. return `short.ly/short_code`

**Redirect:**
1. client hits `short.ly/abc123`
2. look up abc123 → long_url (check cache first!)
3. return HTTP 301/302 redirect

## Scaling bits
- **Cache** the hot short_codes in Redis — most reads never touch the DB.
- **Load balancer** in front of multiple app servers.
- Reads dominate, so **read replicas** help.
- The counter is a bottleneck if single — can pre-allocate ranges of ids to each
  server so they don't fight over one counter.

## Gotchas I'd mention
- 301 (permanent) vs 302 (temporary) redirect — 301 gets cached by browsers so you
  lose click tracking; 302 if you want analytics.
- Expiry / cleanup of old links.
- Custom aliases need a uniqueness check.
