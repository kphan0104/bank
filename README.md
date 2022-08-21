# Bank Account Kata

## Prerequisites
Having Docker installed on your machine is necessary to try out provisioning Cassandra container.

## Provision Cassandra Container

```
λ  docker run -p 9042:9042 --name cassandra cassandra:latest
```

## Connecting to the database from container shell

The easiest way to integrate data is using CQLSH service.
```
λ  docker exec -ti cassandra cqlsh
```

## Perform database operations

```
-- Create keyspace
CREATE KEYSPACE IF NOT EXISTS exaltit WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };

-- Create account table
CREATE TABLE IF NOT EXISTS exaltit.account (
	id uuid PRIMARY KEY,
	balance double
);

-- Create operation table
CREATE TABLE IF NOT EXISTS exaltit.operation (
	account_id uuid,
	id uuid,
	date date,
	type text,
	amount double,
	PRIMARY KEY((account_id), id)
);

-- Insert data
INSERT INTO exaltit.account(id, balance)
VALUES (9c9fa880-55ad-4b71-9a8f-2d3443b10b61, 2000.75);

INSERT INTO exaltit.operation(account_id, id, date, type, amount)
VALUES (9c9fa880-55ad-4b71-9a8f-2d3443b10b61, eb4703d1-ce20-4cf5-ad25-669e11572d2f, '2022-08-19', 'DEPOSIT', 1000.0);

INSERT INTO exaltit.operation(account_id, id, date, type, amount)
VALUES (9c9fa880-55ad-4b71-9a8f-2d3443b10b61, a82f9267-d0db-4ff7-8b9a-6e3c328da965, '2022-08-19', 'DEPOSIT', 1000.75);
```

## Cleanup

```
λ  docker stop cassandra

λ  docker rm cassandra
```