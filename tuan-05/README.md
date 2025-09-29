# 📱 Ứng Dụng Quản Lý Điện Thoại

Ứng dụng web quản lý thông tin sản phẩm điện thoại cho công ty mua bán điện thoại trực tuyến, được xây dựng bằng Servlet/JSP với MariaDB.

## 🛠️ Công nghệ sử dụng

- **Backend**: Java Servlet/JSP
- **Database**: MariaDB (MySQL-compatible)
- **Build Tool**: Maven
- **Web Server**: Apache Tomcat
- **Frontend**: HTML/CSS/JavaScript, JSTL

## 📋 Chức năng chính

### 1. 🔎 Danh sách điện thoại theo nhà cung cấp

- Hiển thị danh sách điện thoại được nhóm theo từng nhà cung cấp
- Tìm kiếm nhà cung cấp theo mã, tên, địa chỉ, số điện thoại
- Xem chi tiết điện thoại của từng nhà cung cấp

### 2. ➕ Thêm mới điện thoại

- Form th robust với validation phía client và server
- Upload hình ảnh với validation file type và size
- Kiểm tra trùng lặp mã điện thoại
- Validation:
  - Mã ĐT: Format DT001, DT002...
  - Năm sản xuất: 4 chữ số từ 1900-2099
  - Cấu hình: Tối đa 255 ký tự
  - Hình ảnh: PNG, JPG, JPEG, tối đa 5MB

### 3. ⚙️ Quản lý và xóa điện thoại

- Hiển thị danh sách tất cả điện thoại
- Xóa điện thoại với xác nhận
- Modal hiển thị hình ảnh lớn

## 🗄️ Cơ sở dữ liệu

### Database: `QUANLYDIENTHOAI`

#### Bảng NHACUNGCAP

```sql
MANCC VARCHAR(10) PRIMARY KEY
TENNHACC VARCHAR(100) NOT NULL
DIACHI VARCHAR(200)
SODIENTHOAI VARCHAR(15)
ENGINE=InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci
```

#### Bảng DIENTHOAI

```sql
MADT VARCHAR(10) PRIMARY KEY
TENDT VARCHAR(100) NOT NULL
NAMSANXUAT INT
CAUHINH VARCHAR(255)
MANCC VARCHAR(10)
HINHANH VARCHAR(255)
FOREIGN KEY (MANCC) REFERENCES NHACUNGCAP(MANCC) ON DELETE CASCADE
CHECK (NAMSANXUAT >= 1900 AND NAMSANXUAT <= 2100)
ENGINE=InnoDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci
```

### ⚠️ Lưu ý về MariaDB

- Database được cấu hình với `utf8mb4` charset để hỗ trợ đầy đủ tiếng Việt
- Sử dụng InnoDB engine để hỗ trợ foreign key constraints và transactions
- Không sử dụng N prefix trong chuỗi (NVARCHAR khác với SQL Server)

## 🚀 Cài đặt và chạy ứng dụng

### 1. Chuẩn bị môi trường

- Java 17+
- Maven 3.6+
- MariaDB Server (hoặc MySQL Server)
- Apache Tomcat 10+

### 2. Cài đặt MariaDB

#### Cài đặt MariaDB Server

```bash
# Ubuntu/Debian
sudo apt update
sudo apt install mariadb-server

# CentOS/RHEL/Fedora
sudo yum install mariadb-server mariadb

# Windows: Download từ https://mariadb.org/download/
```

#### Khởi động MariaDB

```bash
# Ubuntu/Debian
sudo systemctl start mariadb
sudo systemctl enable mariadb

# Tạo database và user
sudo mysql -u root -p
```

### 3. Cài đặt Database

```bash
# Cách 1: Sử dụng MySQL command line
mysql -u root -p < src/main/resources/database_setup.sql

# Cách 2: Truy cập MariaDB bằng MySQL Workbench hoặc Adminer
# Import file database_setup.sql
```

### 4. Cấu hình database connection

Sửa file `src/main/java/iuh/fit/se/tuan05/util/DatabaseUtil.java`:

```java
private static final String DB_URL = "jdbc:mariadb://localhost:3306/QUANLYDIENTHOAI?useUnicode=true&characterEncoding=UTF-8";
private static final String DB_USERNAME = "root"; // Thay đổi username
private static final String DB_PASSWORD = "your_password_here"; // Thay đổi password
```

### 5. Build và deploy

```bash
# Build project
mvn clean compile

# Package thành WAR
mvn package

# Deploy WAR file vào Tomcat
# Copy target/tuan-05-1.0-SNAPSHOT.war vào webapps/
```

### 6. Truy cập ứng dụng

```
http://localhost:8080/tuan-05-1.0-SNAPSHOT/
```

## 📁 Cấu trúc project

```
tuan-05/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── iuh/fit/se/tuan05/
│   │   │       ├── model/
│   │   │       │   ├── NhaCungCap.java
│   │   │       │   └── DienThoai.java
│   │   │       ├── dao/
│   │   │       │   ├── NhaCungCapDAO.java
│   │   │       │   └── DienThoaiDAO.java
│   │   │       ├── servlet/
│   │   │       │   ├── DanhSachDienThoaiNCCServlet.java
│   │   │       │   ├── DienThoaiFormServlet.java
│   │   │       │   └── QuanLyFormServlet.java
│   │   │       └── util/
│   │   │           └── databaseUtil.java
│   │   ├── resources/
│   │   │   └── database_setup.sql
│   │   └── webapp/
│   │       ├── assets/css/
│   │       │   └── style.css
│   │       ├── images/
│   │       ├── layout/
│   │       │   ├── header.jsp
│   │       │   └── footer.jsp
│   │       ├── view/
│   │       │   ├── danhSachDienThoaiNCC.jsp
│   │       │   ├── dienThoaiForm.jsp
│   │       │   └── quanLyForm.jsp
│   │       ├── index.jsp
│   │       └── WEB-INF/
│   │           └── web.xml
│   └── test/
├── pom.xml
└── README.md
```

## 🎨 Tính năng UI/UX

- **Responsive Design**: Hoạt động tốt trên desktop và mobile
- **Modern UI**: Sử dụng Bootstrap-inspired CSS framework tự xây dựng
- **Interactive Elements**: Hover effects, animations, loading states
- **Form Validation**: Client-side và server-side validation
- **Error Handling**: Thông báo lỗi và thành công rõ ràng
- **Image Management**: Upload, preview, hiển thị hình ảnh lớn

## 📝 API Endpoints

| URL                            | Method   | Mô tả                                    |
| ------------------------------ | -------- | ---------------------------------------- |
| `/DanhSachDienThoaiNCCServlet` | GET/POST | Danh sách điện thoại theo NCC + tìm kiếm |
| `/DienThoaiFormServlet`        | GET      | Hiển thị form thêm điện thoại            |
| `/DienThoaiFormServlet`        | POST     | Xử lý thêm điện thoại + upload ảnh       |
| `/QuanLyFormServlet`           | GET      | Hiển thị danh sách quản lý               |
| `/QuanLyFormServlet`           | POST     | Xử lý xóa điện thoại                     |

## 🔧 Troubleshooting

### Lỗi kết nối database

- Kiểm tra MariaDB/MySQL Server đang chạy
- Verify connection string trong DatabaseUtil.java
- Kiểm tra credentials và quyền truy cập database QUANLYDIENTHOAI

### Lỗi upload file

- Kiểm tra folder `/images/` có tồn tại
- Verify file size < 5MB
- Kiểm tra file extension PNG/JPG/JPEG

### Lỗi 404 Not Found

- Verify WAR file đã được deploy đúng
- Kiểm tra Tomcat log
- Đảm bảo servlet annotations đúng

## 📚 Documentation

- Servlet documentation: Xem inline comments trong từng class
- Database schema: Xem file database_setup.sql
- UI components: Xem file style.css và các JSP files

## 👨‍💻 Author

Được phát triển bởi IUH Student - Tuần 05 - Môn Lập Trình Web Java

## 📄 License

Dự án học tập - Sử dụng cho mục đích giáo dục
