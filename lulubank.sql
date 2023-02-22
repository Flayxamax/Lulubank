create database lulubank;

use lulubank;

create table Direcciones(
    id_direccion int PRIMARY KEY AUTO_INCREMENT,
    calle varchar(50) not null,
    colonia varchar(50) not null,
    numero varchar(5) not null
);

create table Clientes(
    id_cliente int PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(50) not null,
    apellido_paterno varchar(30) not null,
    apellido_materno varchar(30) not null,
    fecha_nacimiento date not null,
    edad int not null,
    correo varchar (30) not null unique,
    contrasena blob not null,
    id_direccion int not null,
    foreign key (id_direccion) references Direcciones (id_direccion)
);

create table Cuentas(
    id_cuenta int PRIMARY KEY AUTO_INCREMENT,
    saldo decimal(10,4) default(0) not null,
    fecha_apertura datetime default(current_timestamp()) not null,
    estado enum('activa','desactivada') default('activa'),
    id_cliente int not null,
    foreign key (id_cliente) references Clientes (id_cliente)
);

create table Transferencias(
    id_transferencia int PRIMARY KEY AUTO_INCREMENT,
    fecha_operacion datetime default(current_timestamp()) not null,
    id_cuenta int not null,
    monto decimal(10,4) default(0) not null,
    id_cuentaDestinatario int not null,
    foreign key (id_cuenta) references Cuentas (id_cuenta)
);

create table depositos(
	id_deposito int primary key,
    monto decimal(10,4) default(0) not null,
    fecha_operacion datetime default(current_timestamp()) not null,
    id_cuenta int not null,
    foreign key (id_cuenta) references Cuentas (id_cuenta)
    );

create table Retiros(
    folio int PRIMARY KEY AUTO_INCREMENT,
    contrasena blob not null,
    estado enum ('cobrado', 'noCobrado') default('noCobrado'),
    fecha_creacion datetime default(now()) not null,
    fecha_operacion datetime,
    monto decimal(10,4) default(0) not null,
    id_cuenta int,
    foreign key (id_cuenta) references Cuentas (id_cuenta)
);

#Triggers
DELIMITER //
CREATE TRIGGER generar_id_cuenta BEFORE INSERT ON cuentas
FOR EACH ROW
BEGIN
  DECLARE nuevo_id INT;
  SET nuevo_id = FLOOR(RAND() * 8999) + 1000;
  WHILE (SELECT COUNT(*) FROM cuentas WHERE id_cuenta = nuevo_id) > 0 DO
    SET nuevo_id = FLOOR(RAND() * 8999) + 1000;
  END WHILE;
  SET NEW.id_cuenta = nuevo_id;
END; //
DELIMITER ;

DELIMITER //
CREATE TRIGGER generar_contrasena BEFORE INSERT ON retiros
FOR EACH ROW
BEGIN
    DECLARE contrasena INT;
    SET contrasena = FLOOR(RAND() * 90000000) + 10000000;
    SET NEW.contrasena = AES_ENCRYPT(contrasena, 'hunter2');
END;
//
DELIMITER ;

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `transferencia`(IN manda INT, IN recibe INT, IN montoT decimal(10, 2))
BEGIN

START TRANSACTION;

COMMIT;

-- Obtenemos el saldo actual de la cuenta
SELECT @saldo_actual := saldo FROM cuentas WHERE id_cuenta = manda;

-- Si tiene los fondos suficientes, se realiza
IF @saldo_actual >= montoT THEN
  UPDATE cuentas SET saldo = saldo - montoT WHERE id_cuenta = manda;
  UPDATE cuentas SET saldo = saldo + montoT WHERE id_cuenta = recibe;
END IF;

-- Si algo falla, deshacemos la transacción
IF @saldo_actual < montoT THEN
  ROLLBACK;
ELSE
  COMMIT;
END IF;

END
//
DELIMITER ;

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `transferencia`(IN manda INT, IN recibe INT, IN montoT decimal(10, 2))
BEGIN

START TRANSACTION;

COMMIT;

-- Obtenemos el saldo actual de la cuenta
SELECT @saldo_actual := saldo FROM cuentas WHERE id_cuenta = manda;

-- Si tiene los fondos suficientes, se realiza
IF @saldo_actual >= montoT THEN
  UPDATE cuentas SET saldo = saldo - montoT WHERE id_cuenta = manda;
  UPDATE cuentas SET saldo = saldo + montoT WHERE id_cuenta = recibe;
END IF;

-- Si algo falla, deshacemos la transacción
IF @saldo_actual < montoT THEN
  ROLLBACK;
ELSE
  COMMIT;
END IF;

END
//
DELIMITER ;

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `retiro`(IN folio INT, IN manda INT, IN montoT decimal(10, 2))
BEGIN

START TRANSACTION;

COMMIT;

-- Obtenemos el saldo actual de la cuenta
SELECT @saldo_actual := saldo FROM cuentas WHERE id_cuenta = manda;

-- Si tiene los fondos suficientes, se realiza
IF @saldo_actual >= montoT THEN
  INSERT INTO retiros (folio, monto, id_cuenta) VALUES (folio, montoT,manda);
END IF;

-- Si algo falla, deshacemos la transacción
IF @saldo_actual < montoT THEN
  ROLLBACK;
ELSE
  COMMIT;
END IF;

END
//
DELIMITER ;