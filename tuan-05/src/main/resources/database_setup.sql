-- Script tạo cơ sở dữ liệu QUANLYDIENTHOAI
-- Chạy script này trên MariaDB để tạo database và các bảng

-- Tạo database (UTF8 charset để hỗ trợ tiếng Việt)
CREATE DATABASE QUANLYDIENTHOAI CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE QUANLYDIENTHOAI;

-- Tạo bảng NHACUNGCAP
CREATE TABLE NHACUNGCAP (
    MANCC VARCHAR(10) PRIMARY KEY,
    TENNHACC VARCHAR(100) NOT NULL,
    DIACHI VARCHAR(200),
    SODIENTHOAI VARCHAR(15)
) ENGINE=InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Tạo bảng DIENTHOAI  
CREATE TABLE DIENTHOAI (
    MADT VARCHAR(10) PRIMARY KEY,
    TENDT VARCHAR(100) NOT NULL,
    NAMSANXUAT INT,
    CAUHINH VARCHAR(255),
    MANCC VARCHAR(10),
    HINHANH VARCHAR(255),
    CONSTRAINT FK_DIENTHOAI_NHACUNGCAP FOREIGN KEY (MANCC) REFERENCES NHACUNGCAP(MANCC) ON DELETE CASCADE,
    CONSTRAINT CHK_NAMSANXUAT CHECK (NAMSANXUAT >= 1900 AND NAMSANXUAT <= 2100)
) ENGINE=InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Chèn dữ liệu mẫu cho NHACUNGCAP (không dùng N prefix cho MariaDB)
INSERT INTO NHACUNGCAP (MANCC, TENNHACC, DIACHI, SODIENTHOAI) VALUES
('NCC001', 'Samsung Electronics', 'Số 123 Đường ABC, Quận 1, TP.HCM', '0901234567'),
('NCC002', 'Apple Inc.', 'Số 456 Đường XYZ, Quận 2, TP.HCM', '0907654321'),
('NCC003', 'Oppo Vietnam', 'Số 789 Đường DEF, Quận 3, TP.HCM', '0909876543'),
('NCC004', 'Xiaomi Vietnam', 'Số 321 Đường GHI, Quận 4, TP.HCM', '0904567890'),
('NCC005', 'Huawei Vietnam', 'Số 654 Đường JKL, Quận 5, TP.HCM', '0903210987');

-- Chèn dữ liệu mẫu cho DIENTHOAI (không dùng N prefix cho MariaDB)
INSERT INTO DIENTHOAI (MADT, TENDT, NAMSANXUAT, CAUHINH, MANCC, HINHANH) VALUES
('DT001', 'Samsung Galaxy S24', 2024, 'Chipset: Snapdragon 8 Gen 3, RAM: 8GB, Storage: 256GB, Camera: 200MP', 'NCC001', 'samsung_s24.jpg'),
('DT002', 'iPhone 15 Pro', 2023, 'Chipset: A17 Pro, RAM: 8GB, Storage: 256GB, Camera: 48MP', 'NCC002', 'iphone_15_pro.jpg'),
('DT003', 'Oppo Find X7', 2024, 'Chipset: Snapdragon 8 Gen 2, RAM: 12GB, Storage: 512GB, Camera: 100MP', 'NCC003', 'oppo_find_x7.jpg'),
('DT004', 'Xiaomi 14 Pro', 2024, 'Chipset: Snapdragon 8 Gen 3, RAM: 12GB, Storage: 512GB, Camera: 108MP', 'NCC004', 'xiaomi_14_pro.jpg'),
('DT005', 'Huawei P60 Pro', 2023, 'Chipset: Snapdragon 888, RAM: 8GB, Storage: 256GB, Camera: 50MP', 'NCC005', 'huawei_p60_pro.jpg'),
('DT006', 'Samsung Galaxy A54', 2023, 'Chipset: Exynos 1380, RAM: 8GB, Storage: 128GB, Camera: 50MP', 'NCC001', 'samsung_a54.jpg'),
('DT007', 'iPhone 15', 2023, 'Chipset: A16 Bionic, RAM: 6GB, Storage: 128GB, Camera: 12MP', 'NCC002', 'iphone_15.jpg'),
('DT008', 'Oppo Reno10', 2023, 'Chipset: Snapdragon 778G, RAM: 8GB, Storage: 256GB, Camera: 64MP', 'NCC003', 'oppo_reno10.jpg');
