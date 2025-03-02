-- Script SQL completo para PostgreSQL con datos de prueba
CREATE USER mancomunitat WITH PASSWORD 'mancomunitat';
CREATE DATABASE felinatrack OWNER mancomunitat;

-- Eliminación de tablas
DROP TABLE IF EXISTS colony;
DROP TABLE IF EXISTS cat;
DROP TABLE IF EXISTS responsible;
DROP TABLE IF EXISTS "action";
DROP TABLE IF EXISTS issue;
DROP TABLE IF EXISTS "user";

-- Creación de tablas
CREATE TABLE IF NOT EXISTS colony (
  id SERIAL PRIMARY KEY,
  quantity INT NOT NULL,
  location VARCHAR(255) NOT NULL,
  municipality VARCHAR(100) NOT NULL,
  coordinates VARCHAR(100)
);
-- cat
CREATE TABLE IF NOT EXISTS cat (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  gender VARCHAR(10) NOT NULL,
  color VARCHAR(50),
  breed VARCHAR(50),
  markings TEXT,
  health_condition TEXT,
  colony_id INT REFERENCES colony(id) ON DELETE CASCADE
);
-- responsible
CREATE TABLE IF NOT EXISTS responsible (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  type VARCHAR(50) NOT NULL,
  contact VARCHAR(100),
  colony_id INT REFERENCES colony(id) ON DELETE CASCADE
);
-- action
CREATE TABLE IF NOT EXISTS "action" (
  id SERIAL PRIMARY KEY,
  type VARCHAR(50) NOT NULL,
  date DATE NOT NULL,
  description TEXT,
  colony_id INT REFERENCES colony(id) ON DELETE CASCADE
);
-- issue
CREATE TABLE IF NOT EXISTS issue (
  id SERIAL PRIMARY KEY,
  type VARCHAR(50) NOT NULL,
  description TEXT NOT NULL,
  date DATE NOT NULL,
  colony_id INT REFERENCES colony(id) ON DELETE CASCADE
);
-- user
CREATE TABLE IF NOT EXISTS "user" (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(20) NOT NULL
);

-- Inserts de prueba

INSERT INTO colony (quantity, location, municipality, coordinates) VALUES
(15, 'Parque Central', 'Madrid', '40.4168,-3.7038'),
(20, 'Barrio Norte', 'Barcelona', '41.3851,2.1734'),
(10, 'Zona Sur', 'Valencia', '39.4699,-0.3763'),
(18, 'Centro Histórico', 'Sevilla', '37.3891,-5.9845'),
(25, 'Parque Oeste', 'Bilbao', '43.2630,-2.9350'),
(12, 'Río Verde', 'Granada', '37.1773,-3.5986'),
(22, 'Monte Alto', 'A Coruña', '43.3623,-8.4115'),
(14, 'Puerto Viejo', 'Santander', '43.4623,-3.8099'),
(19, 'Jardines del Sol', 'Málaga', '36.7213,-4.4214'),
(17, 'Barrio Este', 'Zaragoza', '41.6488,-0.8891');

INSERT INTO "user" (name, email, password, role) VALUES
('Admin User', 'admin@felinatrack.com', 'password123', 'ADMIN'),
('Juan Pérez', 'juan@felinatrack.com', 'password123', 'VOLUNTEER'),
('Ana Gómez', 'ana@felinatrack.com', 'password123', 'VOLUNTEER'),
('Carlos Ruiz', 'carlos@felinatrack.com', 'password123', 'NORMAL_USER'),
('Laura Martínez', 'laura@felinatrack.com', 'password123', 'NORMAL_USER'),
('Pedro Sánchez', 'pedro@felinatrack.com', 'password123', 'NORMAL_USER'),
('Lucía Fernández', 'lucia@felinatrack.com', 'password123', 'VOLUNTEER'),
('Manuel Torres', 'manuel@felinatrack.com', 'password123', 'NORMAL_USER'),
('Carmen López', 'carmen@felinatrack.com', 'password123', 'NORMAL_USER'),
('Miguel Ángel', 'miguel@felinatrack.com', 'password123', 'VOLUNTEER');

INSERT INTO cat (name, gender, color, breed, markings, health_condition, colony_id) VALUES
('Michi', 'Male', 'Black', 'Common', 'None', 'Healthy', 1),
('Luna', 'Female', 'White', 'Persian', 'Blue eyes', 'Vaccinated', 1),
('Simba', 'Male', 'Orange', 'Maine Coon', 'Large tail', 'Healthy', 2),
('Nala', 'Female', 'Gray', 'Siamese', 'Pointed ears', 'Dewormed', 3),
('Leo', 'Male', 'Brown', 'Bengal', 'Spotted', 'In treatment', 4),
('Kiara', 'Female', 'Black', 'Bombay', 'Green eyes', 'Healthy', 5),
('Tom', 'Male', 'Gray', 'Russian Blue', 'None', 'Vaccinated', 6),
('Jerry', 'Male', 'White', 'Angora', 'Long fur', 'Healthy', 7),
('Mimi', 'Female', 'Orange', 'Common', 'Small size', 'Dewormed', 8),
('Coco', 'Male', 'Black & White', 'Tuxedo', 'White paws', 'Healthy', 9),
('Kira', 'Female', 'Gray', 'Chartreux', 'Short fur', 'Healthy', 10),
('Rocky', 'Male', 'Brown', 'Common', 'Scar on ear', 'Under observation', 1);

INSERT INTO responsible (name, type, contact, colony_id) VALUES
('Asociación Gatos Madrid', 'association', 'contacto@madridgatos.org', 1),
('Carlos López', 'volunteer', 'carlos@gatos.org', 2),
('Ayuntamiento de Valencia', 'government', 'info@valencia.es', 3),
('María Torres', 'volunteer', 'maria@gatos.org', 4),
('Asociación Felina Sur', 'association', 'sur@gatos.org', 5),
('Pedro Jiménez', 'volunteer', 'pedro@gatos.org', 6),
('Ayuntamiento de Granada', 'government', 'info@granada.es', 7),
('Lucía Herrera', 'volunteer', 'lucia@gatos.org', 8),
('Asociación Norte Gatos', 'association', 'norte@gatos.org', 9),
('Sara Pérez', 'volunteer', 'sara@gatos.org', 10);

INSERT INTO "action" (type, date, description, colony_id) VALUES
('adoption', '2025-02-01', 'Adopted by local family.', 1),
('removal', '2025-01-15', 'Cat relocated to shelter.', 2),
('new_entry', '2025-03-10', 'New cat found near park.', 3),
('adoption', '2025-02-20', 'Successful adoption.', 4),
('removal', '2025-03-01', 'Transferred to veterinary.', 5),
('new_entry', '2025-01-05', 'Kitten added to colony.', 6),
('adoption', '2025-02-14', 'Cat adopted by volunteer.', 7),
('removal', '2025-03-03', 'Emergency removal.', 8),
('new_entry', '2025-02-18', 'Rescued cat entered colony.', 9),
('adoption', '2025-01-30', 'Adopted through program.', 10);

INSERT INTO issue (type, description, date, colony_id) VALUES
('emergency', 'Injured cat found.', '2025-02-02', 1),
('conflict', 'Neighbor complaint about noise.', '2025-01-20', 2),
('emergency', 'Disease outbreak detected.', '2025-03-12', 3),
('conflict', 'Feeding area vandalized.', '2025-02-22', 4),
('emergency', 'Fire near colony zone.', '2025-03-05', 5),
('conflict', 'Unauthorized feeding reported.', '2025-01-10', 6),
('emergency', 'Cat trapped in fence.', '2025-02-16', 7),
('conflict', 'Garbage accumulation issue.', '2025-03-07', 8),
('emergency', 'Poison risk detected.', '2025-02-20', 9),
('conflict', 'Parking lot conflicts with colony.', '2025-01-28', 10);