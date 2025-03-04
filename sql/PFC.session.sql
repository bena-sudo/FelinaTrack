

DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS role_permissions;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS permissions;




-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS users (
  id BIGSERIAL PRIMARY KEY, 
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  phone VARCHAR(15),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- -----------------------------------------------------
-- Table `roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS roles (
  id SERIAL PRIMARY KEY, 
  name VARCHAR(255) NOT NULL UNIQUE,
  description TEXT
);

-- -----------------------------------------------------
-- Table `permissions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS permissions (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE,
  description TEXT
);

-- -----------------------------------------------------
-- Table `user_roles` (Many-to-Many: Users - Roles)
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS user_roles (
  user_id BIGINT NOT NULL,
  role_id INTEGER NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT user_roles_fk_users FOREIGN KEY (user_id)
    REFERENCES users (id) ON DELETE CASCADE,
  CONSTRAINT user_roles_fk_roles FOREIGN KEY (role_id)
    REFERENCES roles (id) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Table `role_permissions` (Many-to-Many: Roles - Permissions)
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS role_permissions (
  role_id INTEGER NOT NULL,
  permission_id INTEGER NOT NULL,
  PRIMARY KEY (role_id, permission_id),
  CONSTRAINT role_permissions_fk_roles FOREIGN KEY (role_id)
    REFERENCES roles (id) ON DELETE CASCADE,
  CONSTRAINT role_permissions_fk_permissions FOREIGN KEY (permission_id)
    REFERENCES permissions (id) ON DELETE CASCADE
);







--------------------------------------------------------



INSERT INTO roles (name, description) VALUES 
('ROLE_ADMIN', 'Full system access, can manage users, roles, and settings'),
('ROLE_VOLUNTEER', 'Can view and manage assigned cat colonies'),
('ROLE_VETERINARIAN', 'Can update cat health records and treatments'),
-- ('ROLE_ADOPTER', 'Can apply for cat adoption'),
('ROLE_MANAGER', 'Oversees multiple colonies and volunteer activities'),
('ROLE_SUPERVISOR', 'Can approve or reject adoptions'),
('ROLE_USER', 'Accesses the platform and views their accreditation history');

INSERT INTO permissions (name, description) VALUES 
('CREATE_COLONY', 'Allows creating a new cat colony'),
('DELETE_CAT', 'Allows removing a cat from the system'),
('UPDATE_HEALTH', 'Allows modifying health records of cats'),
('APPROVE_ADOPTION', 'Allows approving or rejecting adoption requests'),
('VIEW_USERS', 'Allows viewing registered users');