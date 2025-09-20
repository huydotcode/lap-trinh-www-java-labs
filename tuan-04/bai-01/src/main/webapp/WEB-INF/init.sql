-- Tạo database shoppingdb nếu chưa tồn tại
CREATE DATABASE IF NOT EXISTS shoppingdb;
USE shoppingdb;

-- Tạo bảng product
CREATE TABLE IF NOT EXISTS product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    image VARCHAR(255) NOT NULL
);

-- Thêm dữ liệu mẫu
INSERT INTO product (name, price, image) VALUES 
('iPhone 15', 999.99, 'image1.png'),
('Samsung Galaxy S24', 899.99, 'image2.png'),
('MacBook Pro', 1999.99, 'image3.jpeg'),
('Dell XPS 13', 1299.99, 'image4.jpeg'),
('iPad Air', 599.99, 'image5.png'),
('Surface Pro', 1099.99, 'image6.jpeg'),
('AirPods Pro', 249.99, 'image7.jpeg'),
('Sony WH-1000XM5', 399.99, 'image8.png'),
('Apple Watch', 399.99, 'image9.jpeg'),
('Gaming Mouse', 79.99, 'image10.jpeg');
