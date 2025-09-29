<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Quản Lý Điện Thoại - ${pageTitle}</title>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/style.css"
    />
  </head>
  <body>
    <!-- Navigation Bar -->
    <nav class="navbar">
      <div class="navbar-container">
        <a
          href="${pageContext.request.contextPath}/index.jsp"
          class="navbar-brand"
        >
          📱 Quản Lý Điện Thoại
        </a>
        <ul class="navbar-menu">
          <li>
            <a
              href="${pageContext.request.contextPath}/index.jsp"
              class="${requestScope.currentPage == 'home' ? 'active' : ''}"
              >Trang Chủ</a
            >
          </li>
          <li>
            <a
              href="${pageContext.request.contextPath}/DanhSachDienThoaiNCCServlet"
              class="${requestScope.currentPage == 'list' ? 'active' : ''}"
              >Danh Sách Điện Thoại</a
            >
          </li>
          <li>
            <a
              href="${pageContext.request.contextPath}/DienThoaiFormServlet"
              class="${requestScope.currentPage == 'add' ? 'active' : ''}"
              >Thêm Điện Thoại</a
            >
          </li>
          <li>
            <a
              href="${pageContext.request.contextPath}/QuanLyFormServlet"
              class="${requestScope.currentPage == 'manage' ? 'active' : ''}"
              >Quản Lý Sản Phẩm</a
            >
          </li>
        </ul>
      </div>
    </nav>

    <!-- Main Content Container -->
    <div class="container"></div>
  </body>
</html>
