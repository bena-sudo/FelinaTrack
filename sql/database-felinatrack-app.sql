-- Script SQL completo para PostgreSQL con datos de prueba
CREATE USER mancomunitat WITH PASSWORD 'mancomunitat';
CREATE DATABASE felinatrack OWNER mancomunitat;

-- -----------------------------------------------------
-- Eliminación de tablas si ya existen
-- -----------------------------------------------------
DROP TABLE IF EXISTS colonies;
DROP TABLE IF EXISTS cats;
DROP TABLE IF EXISTS responsibles;
DROP TABLE IF EXISTS actions;
DROP TABLE IF EXISTS issues;
DROP TABLE IF EXISTS permissions;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS role_permissions;
DROP TABLE IF EXISTS users;

-- -----------------------------------------------------
-- Tabla colonies
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS colonies (
  id SERIAL PRIMARY KEY,
  quantity INT,
  location VARCHAR(255),
  municipality VARCHAR(255),
  coordinates VARCHAR(255)
);

-- -----------------------------------------------------
-- Tabla cats (gatos)
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cats (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  gender VARCHAR(50),
  color VARCHAR(50),
  breed VARCHAR(50),
  markings TEXT,
  health VARCHAR(50),
  colony_id INT NOT NULL,
  CONSTRAINT fk_colony FOREIGN KEY (colony_id)
    REFERENCES colonies (id) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Tabla users (usuarios del sistema)
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
-- Tabla roles
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS roles (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE,
  description TEXT
);

-- -----------------------------------------------------
-- Tabla permissions
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS permissions (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE,
  description TEXT
);

-- -----------------------------------------------------
-- Tabla responsibles (responsables de colonias)
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS responsibles (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  type VARCHAR(50) NOT NULL CHECK (type IN ('association', 'volunteer', 'government')),
  contact VARCHAR(255),
  user_id BIGINT,
  colony_id INT NOT NULL,
  CONSTRAINT fk_user FOREIGN KEY (user_id)
    REFERENCES users (id) ON DELETE SET NULL,
  CONSTRAINT fk_colony FOREIGN KEY (colony_id)
    REFERENCES colonies (id) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Tabla user_roles (Many-to-Many: Usuarios - Roles)
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS user_roles (
  user_id BIGINT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT fk_user FOREIGN KEY (user_id)
    REFERENCES users (id) ON DELETE CASCADE,
  CONSTRAINT fk_role FOREIGN KEY (role_id)
    REFERENCES roles (id) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Tabla role_permissions (Many-to-Many: Roles - Permissions)
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS role_permissions (
  role_id INT NOT NULL,
  permission_id INT NOT NULL,
  PRIMARY KEY (role_id, permission_id),
  CONSTRAINT fk_role FOREIGN KEY (role_id)
    REFERENCES roles (id) ON DELETE CASCADE,
  CONSTRAINT fk_permission FOREIGN KEY (permission_id)
    REFERENCES permissions (id) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Tabla actions (acciones realizadas por los responsables)
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS actions (
  id SERIAL PRIMARY KEY,
  type VARCHAR(50) NOT NULL CHECK (type IN ('adoption', 'removal', 'new_entry')),
  date TIMESTAMP NOT NULL,
  description TEXT,
  colony_id INT NOT NULL,
  CONSTRAINT fk_colony FOREIGN KEY (colony_id)
    REFERENCES colonies (id) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Tabla issues (problemas asociados a las colonias)
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS issues (
  id SERIAL PRIMARY KEY,
  type VARCHAR(50) NOT NULL CHECK (type IN ('conflict', 'emergency')),
  description TEXT,
  date TIMESTAMP NOT NULL,
  colony_id INT NOT NULL,
  CONSTRAINT fk_colony FOREIGN KEY (colony_id)
    REFERENCES colonies (id) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Tabla responsible_colonies (Many-to-Many: Responsables - Colonias)
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS responsible_colonies (
  responsible_id INT NOT NULL,
  colony_id INT NOT NULL,
  PRIMARY KEY (responsible_id, colony_id),
  CONSTRAINT fk_responsible FOREIGN KEY (responsible_id)
    REFERENCES responsibles (id) ON DELETE CASCADE,
  CONSTRAINT fk_colony FOREIGN KEY (colony_id)
    REFERENCES colonies (id) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Insertar roles en la tabla roles
-- -----------------------------------------------------
INSERT INTO roles (name, description) VALUES 
('ROLE_ADMIN', 'Full system access, can manage users, roles, and settings'),
('ROLE_VOLUNTEER', 'Can view and manage assigned cat colonies'),
('ROLE_COLONY_MANAGER', 'Oversees multiple colonies and volunteer activities'),
('ROLE_USER_MANAGER', 'Can create, assign, and manage users and their roles'),
('ROLE_SUPERVISOR', 'Can approve or reject adoptions'),
('ROLE_USER', 'Accesses the platform and views their accreditation history');

-- -----------------------------------------------------
-- Insertar permisos en la tabla permissions
-- -----------------------------------------------------
INSERT INTO permissions (name, description) VALUES 
('CREATE_COLONY', 'Allows creating a new cat colony'),
('DELETE_CAT', 'Allows removing a cat from the system'),
('UPDATE_HEALTH', 'Allows modifying health records of cats'),
('APPROVE_ADOPTION', 'Allows approving or rejecting adoption requests'),
('VIEW_USERS', 'Allows viewing registered users');

