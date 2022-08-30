INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (1,'Carlos','pena','cp@gmail.com','2020-8-12', '')
INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (2,'dsa','pena','cp@gmail.com','2020-8-12', '')
INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (3,'zxc','pena','cp@gmail.com','2020-8-12', '')
INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (4,'123','pena','cp@gmail.com','2020-8-12', '')
INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (5,'qwe','pena','cp@gmail.com','2020-8-12', '')
INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (6,'ewq','pena','cp@gmail.com','2020-8-12', '')
INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (7,'ghfgh','pena','cp@gmail.com','2020-8-12', '')
INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (8,'vbn','pena','cp@gmail.com','2020-8-12', '')
INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (9,'fgtr','pena','cp@gmail.com','2020-8-12', '')
INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (10,'sdwe','pena','cp@gmail.com','2020-8-12', '')
INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (11,'sasd','pena','cp@gmail.com','2020-8-12', '')
INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (12,'wer','pena','cp@gmail.com','2020-8-12', '')
INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (13,'asdw','pena','cp@gmail.com','2020-8-12', '')
INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (14,'xcvb','pena','cp@gmail.com','2020-8-12', '')
INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (15,'yuij','pena','cp@gmail.com','2020-8-12', '')
INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (16,'hjghj','pena','cp@gmail.com','2020-8-12', '')
INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (17,'tyui','pena','cp@gmail.com','2020-8-12', '')
INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (18,'tyu','pena','cp@gmail.com','2020-8-12', '')
INSERT INTO clientes (id, nombre,apellido,email,create_at,foto) values (19,'ghgfh','pena','cp@gmail.com','2020-8-12', '')


/*Product TABLE*/
INSERT INTO Productos (name,price,create_at) values ('Panasonic Pantalla LCD',299.99,NOW());
INSERT INTO Productos (name,price,create_at) values ('Sony camada 32mpx',599.99,NOW());
INSERT INTO Productos (name,price,create_at) values ('Samsung galaxy S9',899.99,NOW());
INSERT INTO Productos (name,price,create_at) values ('Apple ipod nano',199.99,NOW());
INSERT INTO Productos (name,price,create_at) values ('Asus notebook 3hq',499.99,NOW());
INSERT INTO Productos (name,price,create_at) values ('Impresora multifuncional',99.99,NOW());
INSERT INTO Productos (name,price,create_at) values ('Teclado con lucesitas',49.99,NOW());
INSERT INTO Productos (name,price,create_at) values ('Windows 11 home office english',299.99,NOW());

/* factura*/
INSERT INTO facturas (create_at, description, observation, client_id) values (NOW(), 'cuenta a credito','',1);
INSERT INTO bills_items (bill_id,product_id,lot) values (1,1,1);
INSERT INTO bills_items (bill_id,product_id,lot) values (1,3,1);
INSERT INTO bills_items (bill_id,product_id,lot) values (1,4,1);
INSERT INTO bills_items (bill_id,product_id,lot) values (1,7,1);