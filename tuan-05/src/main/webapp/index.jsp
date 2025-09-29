<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page import="iuh.fit.se.tuan05.dao.*" %> <%@ page
import="java.util.*" %> <% request.setAttribute("pageTitle", "Trang Chủ");
request.setAttribute("currentPage", "home"); %>
<jsp:include page="layout/header.jsp" />

<div class="container">
  <div class="alert alert-info">
    <h2>🧾 Chào mừng đến hệ thống Quản Lý Điện Thoại</h2>
    <p><b>Tài khoản đăng nhập:</b> admin/admin</p>
    <p>Sử dụng menu bên trên để thực hiện các chức năng:</p>
  </div>

  <div class="card">
    <div class="card-header">
      🔎 Danh sách sản phẩm điện thoại theo nhà cung cấp
    </div>
    <div class="card-body">
      <p>
        Danh sách các sản phẩm điện thoại được hiển thị theo từng nhà cung cấp.
      </p>
      <a
        href="${pageContext.request.contextPath}/DanhSachDienThoaiNCCServlet"
        class="btn btn-primary"
      >
        📃 Xem chi tiết ngay
      </a>
    </div>
  </div>

  <div class="card">
    <div class="card-header">➕ Thêm mới sản phẩm điện thoại</div>
    <div class="card-body">
      <p>Thêm mới thông tin điện thoại với đầy đủ thông tin kèm hình ảnh.</p>
      <a
        href="${pageContext.request.contextPath}/DienThoaiFormServlet"
        class="btn btn-success"
      >
        ➕ Thêm mới sản phẩm</a
      >
    </div>
  </div>

  <div class="card">
    <div class="card-header">⚙️ Quản lý và xóa sản phẩm</div>
    <div class="card-body">
      <p>
        Quản lý danh sách tất cả sản phẩm và thực hiện thao tác xóa sản phẩm
        không cần thiết.
      </p>
      <a
        href="${pageContext.request.contextPath}/QuanLyFormServlet"
        class="btn btn-warning"
      >
        ⚙️ Quản lý sản phẩm</a
      >
    </div>
  </div>
</div>

<jsp:include page="layout/footer.jsp" />
