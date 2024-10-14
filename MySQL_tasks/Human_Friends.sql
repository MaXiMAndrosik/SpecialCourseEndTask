CREATE SCHEMA Human_Friends DEFAULT CHARACTER SET utf8 ;
USE Human_Friends;
-- --------------------------------------------------------------------
-- Создать таблицы, соответствующие иерархии из вашей диаграммы классов
-- Заполнить таблицы данными о животных, их командах и датами рождения
CREATE TABLE Animals (
	ClassId INT AUTO_INCREMENT PRIMARY KEY,
	AnimalsClass VARCHAR(20) NOT NULL UNIQUE
);
INSERT INTO Animals (AnimalsClass) VALUES 
('Pets'), ('PackAnimals');

CREATE TABLE Pets (
	TypeId INT AUTO_INCREMENT PRIMARY KEY,
	Type VARCHAR(20) NOT NULL UNIQUE,
	AnimalsClass VARCHAR(20) NOT NULL,
	FOREIGN KEY (AnimalsClass) REFERENCES Animals(AnimalsClass)
);
INSERT INTO Pets (Type, AnimalsClass) VALUES 
('Dog', 'Pets'), ('Cat', 'Pets'), ('Hamster', 'Pets');

CREATE TABLE PackAnimals (
	TypeId INT AUTO_INCREMENT PRIMARY KEY,
	Type VARCHAR(20) NOT NULL UNIQUE,
	AnimalsClass VARCHAR(20) NOT NULL,
	FOREIGN KEY (AnimalsClass) REFERENCES Animals(AnimalsClass)
);
INSERT INTO PackAnimals (Type, AnimalsClass) VALUES 
('Horse', 'PackAnimals'), ('Camel', 'PackAnimals'), ('Donkey', 'PackAnimals');

CREATE TABLE Dogs (
	Id INT AUTO_INCREMENT PRIMARY KEY,
	Name VARCHAR(20) NOT NULL,
	Birthday Date NOT NULL,
	Commands VARCHAR(200) NOT NULL,	#('Sit', 'Stay', 'Roll', 'Fetch', 'Paw', 'Bark'), 
	Type VARCHAR(20) NOT NULL,
	FOREIGN KEY (Type) REFERENCES Pets(Type)
);
INSERT INTO Dogs (Name, Birthday, Commands, Type) VALUES 
('Fido', '2020-01-01', 'Sit, Stay, Fetch', 'Dog'),
('Buddy', '2018-12-10', 'Sit, Paw, Bark', 'Dog'),
('Bella', '2019-11-11', 'Sit, Stay, Roll', 'Dog');

CREATE TABLE Cats (
	Id INT AUTO_INCREMENT PRIMARY KEY,
	Name VARCHAR(20) NOT NULL,
	Birthday Date NOT NULL,
	Commands VARCHAR(200) NOT NULL,
	Type VARCHAR(20) NOT NULL,
	FOREIGN KEY (Type) REFERENCES Pets(Type)
);
INSERT INTO Cats (Name, Birthday, Commands, Type) VALUES 
('Whiskers', '2019-05-15', 'Sit, Pounce', 'Cat'),
('Smudge', '2020-02-20', 'Sit, Pounce, Scratch', 'Cat'),
('Oliver', '2020-06-30', 'Meow, Scratch, Jump', 'Cat');

CREATE TABLE Hamsters (
	Id INT AUTO_INCREMENT PRIMARY KEY,
	Name VARCHAR(20) NOT NULL,
	Birthday Date NOT NULL,
	Commands VARCHAR(200) NOT NULL, 
	Type VARCHAR(20) NOT NULL,
	FOREIGN KEY (Type) REFERENCES Pets(Type)
);
INSERT INTO Hamsters (Name, Birthday, Commands, Type) VALUES 
('Hammy', '2021-03-10', 'Roll, Hide', 'Hamster'),
('Peanut', '2021-08-01', 'Roll, Spin', 'Hamster');

CREATE TABLE Horses (
	Id INT AUTO_INCREMENT PRIMARY KEY,
	Name VARCHAR(20) NOT NULL,
	Birthday Date NOT NULL,
	Commands VARCHAR(200) NOT NULL, 
	Type VARCHAR(20) NOT NULL,
	FOREIGN KEY (Type) REFERENCES PackAnimals(Type)
);
INSERT INTO Horses (Name, Birthday, Commands, Type) VALUES 
('Thunder', '2015-07-21', 'Trot, Canter, Gallop', 'Horse'),
('Storm', '2014-05-05', 'Trot, Canter', 'Horse'),
('Blaze', '2016-02-29', 'Trot, Jump, Gallop', 'Horse');

CREATE TABLE Camels (
	Id INT AUTO_INCREMENT PRIMARY KEY,
	Name VARCHAR(20) NOT NULL,
	Birthday Date NOT NULL,
	Commands VARCHAR(200) NOT NULL, 
	Type VARCHAR(20) NOT NULL,
	FOREIGN KEY (Type) REFERENCES PackAnimals(Type)
);
INSERT INTO Camels (Name, Birthday, Commands, Type) VALUES 
('Sandy', '2016-11-03', 'Walk, Carry Load', 'Camel'),
('Dune', '2018-12-12', 'TWalk, Sit', 'Camel'),
('Sahara', '2015-08-14', 'Walk, Run', 'Camel');

CREATE TABLE Donkeys (
	Id INT AUTO_INCREMENT PRIMARY KEY,
	Name VARCHAR(20) NOT NULL,
	Birthday Date NOT NULL,
	Commands VARCHAR(200) NOT NULL, 
	Type VARCHAR(20) NOT NULL,
	FOREIGN KEY (Type) REFERENCES PackAnimals(Type)
);
INSERT INTO Donkeys (Name, Birthday, Commands, Type) VALUES 
('Eeyore', '2017-09-18', 'Walk, Carry Load, Bray', 'Donkey'),
('Burro', '2019-01-23', 'Walk, Bray, Kick', 'Donkey');

SELECT * FROM Pets;

-- --------------------------------------------------------------------
-- output all classes and types
SELECT Animals.AnimalsClass, Pets.Type as Type  FROM Animals
JOIN Pets ON Animals.AnimalsClass = Pets.AnimalsClass
UNION
SELECT Animals.AnimalsClass, PackAnimals.Type as Type  FROM Animals
JOIN PackAnimals ON Animals.AnimalsClass = PackAnimals.AnimalsClass;
-- output all Pets
SELECT Pets.AnimalsClass, Pets.Type AS ClassType, Dogs.Name AS Name,
		Dogs.Birthday AS Birthday, Dogs.Commands AS Commands 
FROM Pets
JOIN Dogs ON Pets.Type = Dogs.Type
UNION 
SELECT Pets.AnimalsClass, Pets.Type AS ClassType, Cats.Name AS Name,
		Cats.Birthday AS Birthday, Cats.Commands AS Commands 
FROM Pets
JOIN Cats ON Pets.Type = Cats.Type
UNION
SELECT Pets.AnimalsClass, Pets.Type AS ClassType, Hamsters.Name AS Name,
		Hamsters.Birthday AS Birthday, Hamsters.Commands AS Commands
FROM Pets
JOIN Hamsters ON Pets.Type = Hamsters.Type;
-- output all PackAnimals
SELECT PackAnimals.AnimalsClass, PackAnimals.Type AS ClassType,
		Horses.Name AS Name, Horses.Birthday AS Birthday, Horses.Commands AS Commands 
FROM PackAnimals
JOIN Horses ON PackAnimals.Type = Horses.Type
UNION 
SELECT PackAnimals.AnimalsClass, PackAnimals.Type AS ClassType,
		Camels.Name AS Name, Camels.Birthday AS Birthday, Camels.Commands AS Commands 
FROM PackAnimals
JOIN Camels ON PackAnimals.Type = Camels.Type
UNION
SELECT PackAnimals.AnimalsClass, PackAnimals.Type AS ClassType,
		Donkeys.Name AS Name, Donkeys.Birthday AS Birthday, Donkeys.Commands AS Commands
FROM PackAnimals
JOIN Donkeys ON PackAnimals.Type = Donkeys.Type;


-- --------------------------------------------------------------------
-- Удалить записи о верблюдах и объединить таблицы лошадей и ослов
-- delete all records from Camels
DELETE FROM Camels;

-- union tables "Horses" and "Donkeys" and "Camels" variant with VIEW
DROP VIEW IF EXISTS AllPackAnimalsVIEW;
DELIMITER //
CREATE VIEW AllPackAnimalsVIEW AS
SELECT Animals.AnimalsClass, PackAnimals.Type AS ClassType, Horses.Name AS Name, Horses.Birthday AS Birthday, Horses.Commands AS Commands 
FROM Animals
JOIN PackAnimals ON Animals.AnimalsClass = PackAnimals.AnimalsClass
JOIN Horses ON PackAnimals.Type = Horses.Type
UNION 
SELECT Animals.AnimalsClass, PackAnimals.Type AS ClassType, Camels.Name AS Name, Camels.Birthday AS Birthday, Camels.Commands AS Commands 
FROM Animals
JOIN PackAnimals ON Animals.AnimalsClass = PackAnimals.AnimalsClass
JOIN Camels ON PackAnimals.Type = Camels.Type
UNION
SELECT Animals.AnimalsClass, PackAnimals.Type AS ClassType, Donkeys.Name AS Name, Donkeys.Birthday AS Birthday, Donkeys.Commands AS Commands
FROM Animals
JOIN PackAnimals ON Animals.AnimalsClass = PackAnimals.AnimalsClass
JOIN Donkeys ON PackAnimals.Type = Donkeys.Type;
//
DELIMITER ;
SELECT * FROM AllPackAnimalsVIEW;

-- union tables "Horses" and "Donkeys" and "Camels" variant with new table
DROP TABLE IF EXISTS AllPackAnimals;
CREATE TABLE AllPackAnimals (
	Id INT AUTO_INCREMENT PRIMARY KEY,
	Name VARCHAR(20) NOT NULL,
	Birthday Date NOT NULL,
	Commands VARCHAR(200) NOT NULL, 
	Type VARCHAR(20) NOT NULL,
	FOREIGN KEY (Type) REFERENCES PackAnimals(Type)
);
INSERT INTO AllPackAnimals (Name, Birthday, Commands, Type)
SELECT Horses.Name AS Name, Horses.Birthday AS Birthday, Horses.Commands AS Commands, PackAnimals.Type AS ClassType 
FROM PackAnimals
JOIN Horses ON PackAnimals.Type = Horses.Type
UNION 
SELECT Camels.Name AS Name, Camels.Birthday AS Birthday, Camels.Commands AS Commands, PackAnimals.Type
FROM PackAnimals
JOIN Camels ON PackAnimals.Type = Camels.Type
UNION
SELECT Donkeys.Name AS Name, Donkeys.Birthday AS Birthday, Donkeys.Commands AS Commands, PackAnimals.Type 
FROM PackAnimals
JOIN Donkeys ON PackAnimals.Type = Donkeys.Type;

SELECT * FROM AllPackAnimals;


-- --------------------------------------------------------------------
-- Создать новую таблицу для животных в возрасте от 1 до 3 лет и вычислить
-- их возраст с точностью до месяца

-- Создадим временную таблицу VIEW куда поместим всех животных
-- all animals VIEW
DROP VIEW IF EXISTS AllAnimalsVIEW;
DELIMITER //
CREATE VIEW AllAnimalsVIEW AS
SELECT Pets.AnimalsClass, Pets.Type AS ClassType, Dogs.Name AS Name,
		Dogs.Birthday AS Birthday, Dogs.Commands AS Commands 
FROM Pets
JOIN Dogs ON Pets.Type = Dogs.Type
UNION 
SELECT Pets.AnimalsClass, Pets.Type AS ClassType, Cats.Name AS Name,
		Cats.Birthday AS Birthday, Cats.Commands AS Commands 
FROM Pets
JOIN Cats ON Pets.Type = Cats.Type
UNION
SELECT Pets.AnimalsClass, Pets.Type AS ClassType, Hamsters.Name AS Name,
		Hamsters.Birthday AS Birthday, Hamsters.Commands AS Commands
FROM Pets
JOIN Hamsters ON Pets.Type = Hamsters.Type
UNION
SELECT PackAnimals.AnimalsClass, PackAnimals.Type AS ClassType,
		Horses.Name AS Name, Horses.Birthday AS Birthday, Horses.Commands AS Commands 
FROM PackAnimals
JOIN Horses ON PackAnimals.Type = Horses.Type
UNION 
SELECT PackAnimals.AnimalsClass, PackAnimals.Type AS ClassType,
		Camels.Name AS Name, Camels.Birthday AS Birthday, Camels.Commands AS Commands 
FROM PackAnimals
JOIN Camels ON PackAnimals.Type = Camels.Type
UNION
SELECT PackAnimals.AnimalsClass, PackAnimals.Type AS ClassType,
		Donkeys.Name AS Name, Donkeys.Birthday AS Birthday, Donkeys.Commands AS Commands
FROM PackAnimals
JOIN Donkeys ON PackAnimals.Type = Donkeys.Type;
//
DELIMITER ;
SELECT * FROM AllAnimalsVIEW;

-- так как исходные даты рождения животных превышают возраст 3 года
-- берем животных в возрасте от 1 до 4 лет!!!
-- table with animals between 1 and 4 years and age_months
CREATE TABLE SmallAnimals (
	Id INT AUTO_INCREMENT PRIMARY KEY,
	AnimalsClass VARCHAR(20) NOT NULL,
	Type VARCHAR(20) NOT NULL,
	Name VARCHAR(20) NOT NULL,
	Birthday Date NOT NULL,
	Commands VARCHAR(200) NOT NULL,
	Age INT NOT NULL
);
DELETE FROM SmallAnimals;

INSERT INTO SmallAnimals (AnimalsClass, Type, Name, Birthday, Commands, Age)
SELECT 	AllAnimalsVIEW.AnimalsClass AS AnimalsClass,
		AllAnimalsVIEW.ClassType AS Type,
		AllAnimalsVIEW.Name AS Name,
		AllAnimalsVIEW.Birthday AS Birthday,
		AllAnimalsVIEW.Commands AS Commands, 
		IF (DAY(CURDATE()) - DAY(Birthday) > 0,
			YEAR(CURDATE()) * 12 + MONTH(CURDATE()) -
				YEAR(Birthday) * 12 - MONTH(Birthday),
			YEAR(CURDATE()) * 12 + MONTH(CURDATE()) -
				YEAR(Birthday) * 12 - MONTH(Birthday) - 1) AS Age
FROM AllAnimalsVIEW
WHERE	DATEDIFF(CURDATE(),DATE_ADD(Birthday, INTERVAL 1 YEAR)) > 0 and 
		DATEDIFF(CURDATE(),DATE_ADD(Birthday, INTERVAL 4 YEAR)) < 0;

SELECT * FROM SmallAnimals;


-- --------------------------------------------------------------------
-- Объединить все созданные таблицы в одну, сохраняя информацию
-- о принадлежности к исходным таблицам
-- all animals
DROP TABLE IF EXISTS AllAnimals;
CREATE TABLE AllAnimals (
	Id INT AUTO_INCREMENT PRIMARY KEY,
	AnimalsClass VARCHAR(20) NOT NULL,
	PetsType VARCHAR(20),
	PackAnimalsType VARCHAR(20),
	Name VARCHAR(20) NOT NULL,
	Birthday Date NOT NULL,
	Commands VARCHAR(200) NOT NULL, 
	FOREIGN KEY (AnimalsClass) REFERENCES Animals(AnimalsClass),
	FOREIGN KEY (PetsType) REFERENCES Pets(Type),
	FOREIGN KEY (PackAnimalsType) REFERENCES PackAnimals(Type)
);
INSERT INTO AllAnimals (AnimalsClass,PetsType,PackAnimalsType,Name,Birthday,Commands)
SELECT Pets.AnimalsClass, Pets.Type AS PetsType, NULL, Dogs.Name AS Name,
		Dogs.Birthday AS Birthday, Dogs.Commands AS Commands 
FROM Pets
JOIN Dogs ON Pets.Type = Dogs.Type
UNION 
SELECT Pets.AnimalsClass, Pets.Type AS PetsType, NULL, Cats.Name AS Name,
		Cats.Birthday AS Birthday, Cats.Commands AS Commands 
FROM Pets
JOIN Cats ON Pets.Type = Cats.Type
UNION
SELECT Pets.AnimalsClass, Pets.Type AS PetsType, NULL, Hamsters.Name AS Name,
		Hamsters.Birthday AS Birthday, Hamsters.Commands AS Commands
FROM Pets
JOIN Hamsters ON Pets.Type = Hamsters.Type
UNION
SELECT PackAnimals.AnimalsClass, NULL, PackAnimals.Type AS PackAnimalsType,
		Horses.Name AS Name, Horses.Birthday AS Birthday, Horses.Commands AS Commands 
FROM PackAnimals
JOIN Horses ON PackAnimals.Type = Horses.Type
UNION 
SELECT PackAnimals.AnimalsClass, NULL, PackAnimals.Type AS PackAnimalsType,
		Camels.Name AS Name, Camels.Birthday AS Birthday, Camels.Commands AS Commands 
FROM PackAnimals
JOIN Camels ON PackAnimals.Type = Camels.Type
UNION
SELECT PackAnimals.AnimalsClass, NULL, PackAnimals.Type AS PackAnimalsType,
		Donkeys.Name AS Name, Donkeys.Birthday AS Birthday, Donkeys.Commands AS Commands
FROM PackAnimals
JOIN Donkeys ON PackAnimals.Type = Donkeys.Type;