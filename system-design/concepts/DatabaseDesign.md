# Database Design (notes)

My notes on picking and scaling databases. Keeping it simple.

## SQL vs NoSQL
- **SQL (relational)** — tables, rows, fixed schema, JOINs, ACID transactions.
  Postgres, MySQL. Great when data is structured and relationships matter (banking,
  orders).
- **NoSQL** — flexible schema. a few flavors:
  - Document (MongoDB) — JSON-ish docs.
  - Key-value (Redis, DynamoDB) — simple, super fast lookups.
  - Wide-column (Cassandra) — huge scale, write-heavy.
  - Graph (Neo4j) — relationships as first class (social networks).
  Great when you need massive scale or the schema keeps changing.

Rule of thumb: start with SQL unless you have a clear reason not to.

## Indexing
An index is like the index at the back of a book — lets the DB find rows without
scanning the whole table. Usually a B-tree under the hood.
- Speeds up reads (WHERE, JOIN, ORDER BY).
- Slows down writes a bit (index has to be updated too) and uses extra space.
- Don't index everything — only the columns you actually query on.

## Normalization vs Denormalization
- **Normalize** — split data into related tables, no duplication. clean, less
  storage, but more JOINs.
- **Denormalize** — duplicate some data to avoid expensive JOINs. faster reads,
  but you have to keep the copies in sync.

## Scaling a database
- **Replication** — copies of the same data. usually one primary (writes) + several
  replicas (reads). spreads read load + gives backups.
- **Sharding** — split DIFFERENT data across DBs (see SystemDesignBasics). for when
  one machine can't hold it all.
- **Read replicas** are the easy first win for read-heavy apps.

## ACID vs BASE
- **ACID** (SQL) — Atomic, Consistent, Isolated, Durable. strong guarantees.
- **BASE** (many NoSQL) — Basically Available, Soft state, Eventually consistent.
  trades strict consistency for scale + availability.

## Quick decision checklist
- Lots of relationships + need transactions? → SQL.
- Massive scale, flexible/changing schema? → NoSQL.
- Read-heavy? → add caching + read replicas.
- Too big for one box? → shard.
