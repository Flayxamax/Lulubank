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
    saldo decimal(8,4) default(0) not null,
    fecha_apertura datetime default(current_timestamp()) not null,
    estado enum('activa','desactivada') default('activa'),
    id_cliente int not null,
    foreign key (id_cliente) references Clientes (id_cliente)
);

create table Transferencias(
    id_transferencia int PRIMARY KEY AUTO_INCREMENT,
    fecha_operacion datetime default(current_timestamp()) not null,
    id_cuenta int not null,
    foreign key (id_cuenta) references Cuentas (id_cuenta)
);

create table depositos(
	id_deposito int primary key,
    monto decimal(8,4) not null,
    fecha_operacion datetime default(current_timestamp()) not null,
    id_cuenta int not null,
    foreign key (id_cuenta) references Cuentas (id_cuenta)
    );

create table Retiros(
    folio int PRIMARY KEY AUTO_INCREMENT,
    contrasena varchar(8) not null,
    estado enum ('cobrado', 'noCobrado') default('noCobrado'),
    fecha_operacion datetime,
    monto decimal(8,4) not null,
    id_cuenta int,
    foreign key (id_cuenta) references Cuentas (id_cuenta)
);
