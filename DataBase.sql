CREATE TABLE `angajati` (
	`CNP` VARCHAR(255) NOT NULL ,
	`CodBanca` VARCHAR(255) NOT NULL,
	`Nume` VARCHAR(255) NOT NULL,
	`Prenume` VARCHAR(255) NOT NULL,
	`CodAngajat` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`CNP`)
);
CREATE TABLE `clienti` (
	`CNP` VARCHAR(255) NOT NULL ,
	`CodBanca` VARCHAR(255) NOT NULL,
	`Nume` VARCHAR(255) NOT NULL,
	`Prenume` VARCHAR(255) NOT NULL,
	`Adresa` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`CNP`)
);
CREATE TABLE `banci` (
	`CodBanca` VARCHAR(255) NOT NULL,
	`DenumireBanca` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`CodBanca`)
);
CREATE TABLE `conturieconomii` (
	`NumarCont` VARCHAR(255) NOT NULL ,
	`CnpDetinator` VARCHAR(255) NOT NULL,
	`Valuta` VARCHAR(255) NOT NULL,
	`Tip` INTEGER NOT NULL,
	`Suma` FLOAT NOT NULL,
	`Dobanda` FLOAT NOT NULL,
	PRIMARY KEY (`NumarCont`)
);
