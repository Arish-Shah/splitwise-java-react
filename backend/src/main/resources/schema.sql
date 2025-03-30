CREATE TABLE IF NOT EXISTS users (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	name VARCHAR(255) NOT NULL,
	email VARCHAR(255) UNIQUE NOT NULL,
	password TEXT NOT NULL,
	created_at TIMESTAMP DEFAULT NOW(),
	updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS transactions (
	id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
	requester_id UUID NOT NULL REFERENCES users(id),
	payer_id UUID NOT NULL REFERENCES users(id),
	amount DECIMAL(10, 2) NOT NULL,
	status VARCHAR(10) NOT NULL,
	description TEXT,
	created_at TIMESTAMP DEFAULT NOW(),
	updated_at TIMESTAMP DEFAULT NOW()
);
