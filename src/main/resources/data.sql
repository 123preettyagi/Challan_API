-- Create users table
CREATE TABLE IF NOT EXISTS users 
(
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20)
);

-- Create challans table
CREATE TABLE IF NOT EXISTS challans 
(
    id BIGSERIAL PRIMARY KEY,
    violation_type VARCHAR(100) NOT NULL,
    vehicle_number VARCHAR(20) NOT NULL,
    location VARCHAR(200) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    violation_date TIMESTAMP NOT NULL,
    payment_date TIMESTAMP,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    user_id BIGINT NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Insert default admin user (password: admin123)
INSERT INTO users (username, password, role, full_name, email, phone) 
VALUES ('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVZ4tG', 'ADMIN', 'System Administrator', 'admin@system.com', '1234567890')
ON CONFLICT (username) DO NOTHING;

-- Insert default user (password: user123)
INSERT INTO users (username, password, role, full_name, email, phone) 
VALUES ('user', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVZ4tG', 'USER', 'Test User', 'user@test.com', '9876543210')
ON CONFLICT (username) DO NOTHING;