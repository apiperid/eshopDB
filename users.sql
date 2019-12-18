/*
  Admin can do everything in EshopDB
*/

CREATE USER 'Admin'@'localhost' IDENTIFIED BY 'Admin';
GRANT ALL PRIVILEGES ON EshopDB.* TO 'Admin'@'localhost';


/*
  Employee can:
  search for users ( only Customers )
  see details about users ( only Customers )
  search for products
  search for orders
  change the status ( Progress ) of an order
  see all the details of an orders
  add / delete a product
  update the stock of a product
  
*/

CREATE USER 'Employee'@'localhost' IDENTIFIED BY 'Employee';
GRANT SELECT ON EshopDB.user TO 'Employee'@'localhost';
GRANT SELECT , INSERT , DELETE ON EshopDB.cpus TO 'Employee'@'localhost';
GRANT SELECT , INSERT , DELETE ON EshopDB.gpu TO 'Employee'@'localhost';
GRANT SELECT , INSERT , DELETE ON EshopDB.ram TO 'Employee'@'localhost';
GRANT SELECT , INSERT , DELETE ON EshopDB.psu TO 'Employee'@'localhost';
GRANT SELECT , INSERT , DELETE ON EshopDB.tower TO 'Employee'@'localhost';
GRANT SELECT , INSERT , DELETE ON EshopDB.computer TO 'Employee'@'localhost';
GRANT SELECT , INSERT , DELETE ON EshopDB.motherboard TO 'Employee'@'localhost';
GRANT SELECT , INSERT , DELETE ON EshopDB.harddisk TO 'Employee'@'localhost';
GRANT SELECT , INSERT , DELETE , UPDATE ON EshopDB.product TO 'Employee'@'localhost';
GRANT SELECT ON EshopDB.order_contains_product TO 'Employee'@'localhost';
GRANT SELECT , UPDATE ON EshopDB.orders TO 'Employee'@'localhost';


/*
  Guest can only see our products
  so he can do only select to our products
*/

CREATE USER 'Guest'@'%' IDENTIFIED BY 'Guest';
GRANT SELECT ON EshopDB.cpus TO 'Guest'@'%';
GRANT SELECT ON EshopDB.gpu TO 'Guest'@'%';
GRANT SELECT ON EshopDB.ram TO 'Guest'@'%';
GRANT SELECT ON EshopDB.psu TO 'Guest'@'%';
GRANT SELECT ON EshopDB.tower TO 'Guest'@'%';
GRANT SELECT ON EshopDB.computer TO 'Guest'@'%';
GRANT SELECT ON EshopDB.motherboard TO 'Guest'@'%';
GRANT SELECT ON EshopDB.harddisk TO 'Guest'@'%';
GRANT SELECT ON EshopDB.product TO 'Guest'@'%';


/*
  Customer can:
  search product
  make an order
  see details of his own orders
*/

/* every record in the table user is a customer
*/

CREATE USER 'apiperid'@'%' IDENTIFIED BY '123456789';
GRANT SELECT ON EshopDB.cpus TO 'apiperid'@'%';
GRANT SELECT ON EshopDB.gpu TO 'apiperid'@'%';
GRANT SELECT ON EshopDB.ram TO 'apiperid'@'%';
GRANT SELECT ON EshopDB.psu TO 'apiperid'@'%';
GRANT SELECT ON EshopDB.tower TO 'apiperid'@'%';
GRANT SELECT ON EshopDB.computer TO 'apiperid'@'%';
GRANT SELECT ON EshopDB.motherboard TO 'apiperid'@'%';
GRANT SELECT ON EshopDB.harddisk TO 'apiperid'@'%';
GRANT SELECT ON EshopDB.product TO 'apiperid'@'%';
GRANT SELECT , INSERT ON EshopDB.orders TO 'apiperid'@'%';
GRANT SELECT , INSERT ON EshopDB.order_contains_product TO 'apiperid'@'%';

CREATE USER 'knikolla'@'%' IDENTIFIED BY 'qwerty_@';
GRANT SELECT ON EshopDB.cpus TO 'knikolla'@'%';
GRANT SELECT ON EshopDB.gpu TO 'knikolla'@'%';
GRANT SELECT ON EshopDB.ram TO 'knikolla'@'%';
GRANT SELECT ON EshopDB.psu TO 'knikolla'@'%';
GRANT SELECT ON EshopDB.tower TO 'knikolla'@'%';
GRANT SELECT ON EshopDB.computer TO 'knikolla'@'%';
GRANT SELECT ON EshopDB.motherboard TO 'knikolla'@'%';
GRANT SELECT ON EshopDB.harddisk TO 'knikolla'@'%';
GRANT SELECT ON EshopDB.product TO 'knikolla'@'%';
GRANT SELECT , INSERT ON EshopDB.orders TO 'knikolla'@'%';
GRANT SELECT , INSERT ON EshopDB.order_contains_product TO 'knikolla'@'%';

CREATE USER 'papachri'@'%' IDENTIFIED BY '987654321';
GRANT SELECT ON EshopDB.cpus TO 'papachri'@'%';
GRANT SELECT ON EshopDB.gpu TO 'papachri'@'%';
GRANT SELECT ON EshopDB.ram TO 'papachri'@'%';
GRANT SELECT ON EshopDB.psu TO 'papachri'@'%';
GRANT SELECT ON EshopDB.tower TO 'papachri'@'%';
GRANT SELECT ON EshopDB.computer TO 'papachri'@'%';
GRANT SELECT ON EshopDB.motherboard TO 'papachri'@'%';
GRANT SELECT ON EshopDB.harddisk TO 'papachri'@'%';
GRANT SELECT ON EshopDB.product TO 'papachri'@'%';
GRANT SELECT , INSERT ON EshopDB.orders TO 'papachri'@'%';
GRANT SELECT , INSERT ON EshopDB.order_contains_product TO 'papachri'@'%';

CREATE USER 'user123'@'%' IDENTIFIED BY 'dgsdfssfsad';
GRANT SELECT ON EshopDB.cpus TO 'user123'@'%';
GRANT SELECT ON EshopDB.gpu TO 'user123'@'%';
GRANT SELECT ON EshopDB.ram TO 'user123'@'%';
GRANT SELECT ON EshopDB.psu TO 'user123'@'%';
GRANT SELECT ON EshopDB.tower TO 'user123'@'%';
GRANT SELECT ON EshopDB.computer TO 'user123'@'%';
GRANT SELECT ON EshopDB.motherboard TO 'user123'@'%';
GRANT SELECT ON EshopDB.harddisk TO 'user123'@'%';
GRANT SELECT ON EshopDB.product TO 'user123'@'%';
GRANT SELECT , INSERT ON EshopDB.orders TO 'user123'@'%';
GRANT SELECT , INSERT ON EshopDB.order_contains_product TO 'user123'@'%';

CREATE USER 'user2'@'%' IDENTIFIED BY '539t8gufjhs';
GRANT SELECT ON EshopDB.cpus TO 'user2'@'%';
GRANT SELECT ON EshopDB.gpu TO 'user2'@'%';
GRANT SELECT ON EshopDB.ram TO 'user2'@'%';
GRANT SELECT ON EshopDB.psu TO 'user2'@'%';
GRANT SELECT ON EshopDB.tower TO 'user2'@'%';
GRANT SELECT ON EshopDB.computer TO 'user2'@'%';
GRANT SELECT ON EshopDB.motherboard TO 'user2'@'%';
GRANT SELECT ON EshopDB.harddisk TO 'user2'@'%';
GRANT SELECT ON EshopDB.product TO 'user2'@'%';
GRANT SELECT , INSERT ON EshopDB.orders TO 'user2'@'%';
GRANT SELECT , INSERT ON EshopDB.order_contains_product TO 'user2'@'%';

CREATE USER 'user3'@'%' IDENTIFIED BY 'fhskdf3428';
GRANT SELECT ON EshopDB.cpus TO 'user3'@'%';
GRANT SELECT ON EshopDB.gpu TO 'user3'@'%';
GRANT SELECT ON EshopDB.ram TO 'user3'@'%';
GRANT SELECT ON EshopDB.psu TO 'user3'@'%';
GRANT SELECT ON EshopDB.tower TO 'user3'@'%';
GRANT SELECT ON EshopDB.computer TO 'user3'@'%';
GRANT SELECT ON EshopDB.motherboard TO 'user3'@'%';
GRANT SELECT ON EshopDB.harddisk TO 'user3'@'%';
GRANT SELECT ON EshopDB.product TO 'user3'@'%';
GRANT SELECT , INSERT ON EshopDB.orders TO 'user3'@'%';
GRANT SELECT , INSERT ON EshopDB.order_contains_product TO 'user3'@'%';





