<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp" />

<div class="container">
  <div class="card">
    <div class="card-header">⚙️ Quản Lý Điện Thoại</div>
    <div class="card-body">
      
      <!-- Hiển thị thông báo thành công -->
      <c:if test="${success != null}">
        <div class="alert alert-success">
          ✅ ${success}
        </div>
      </c:if>
      
      <!-- Hiển thị lỗi -->
      <c:if test="${error != null}">
        <div class="alert alert-danger">
          ⚠️ ${error}
        </div>
      </c:if>
      
      <!-- Form xóa điện thoại -->
      <form action="${pageContext.request.contextPath}/QuanLyFormServlet" method="post" 
            onsubmit="return confirmDelete('Bạn có chắc chắn muốn xóa điện thoại này không?\n\nThao tác này không thể hoàn tác!')">
        
        <div class="form-group">
          <label for="maDT" class="form-label">Hãy chọn điện thoại cần xóa:</label>
          <select id="maDT" name="maDT" class="form-control" required>
            <option value="">-- Chọn điện thoại cần xóa --</option>
            <c:forEach var="dt" items="${allDienThoai}">
              <option value="${dt.maDT}">
                ${dt.maDT} - ${dt.tenDT} (${dt.nhaCungCap.tenNHacc})
              </option>
            </c:forEach>
          </select>
        </div>
        
        <div class="d-flex gap-2">
          <button type="submit" class="btn btn-danger">
            🗑️ Xóa Điện Thoại
          </button>
          <a href="${pageContext.request.contextPath}/DienThoaiFormServlet" 
             class="btn btn-success">
            ➕ Thêm Điện Thoại Mới
          </a>
          <a href="${pageContext.request.contextPath}/DanhSachDienThoaiNCCServlet" 
             class="btn btn-primary">
            📃 Xem Danh Sách Theo NCC
          </a>
        </div>
      </form>
    </div>
  </div>
  
  <!-- Danh sách tất cả điện thoại -->
  <c:if test="${allDienThoai != null && not empty allDienThoai}">
    <div class="card">
      <div class="card-header">
        📱 Danh Sách Tất Cả Điện Thoại (${allDienThoai.size()} sản phẩm)
      </div>
      <div class="card-body">
        <div class="table-responsive">
          <table class="table table-striped table-hover">
            <thead>
              <tr>
                <th>Mã ĐT</th>
                <th>Tên ĐT</th>
                <th>Năm SX</th>
                <th>Cấu hình</th>
                <th>Nhà CC</th>
                <th>Hình ảnh</th>
                <th>Thao tác</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="dt" items="${allDienThoai}">
                <tr>
                  <td><strong>${dt.maDT}</strong></td>
                  <td>${dt.tenDT}</td>
                  <td>${dt.namSanXuat}</td>
                  <td>
                    <span class="text-truncate d-inline-block" style="max-width: 200px;" 
                          title="${dt.cauHinh}">
                      ${dt.cauHinh}
                    </span>
                  </td>
                  <td>
                    <strong>${dt.nhaCungCap.tenNHacc}</strong><br>
                    <small class="text-muted">${dt.maNCC}</small>
                  </td>
                  <td>
                    <c:choose>
                      <c:when test="${not empty dt.hinhAnh}">
                        <img src="${pageContext.request.contextPath}/images/${dt.hinhAnh}"
                             class="product-image" alt="${dt.tenDT}" 
                             onclick="showImageModal('${pageContext.request.contextPath}/images/${dt.hinhAnh}', '${dt.tenDT}')" 
                             style="cursor: pointer;" />
                      </c:when>
                      <c:otherwise>
                        <div class="text-muted text-center">
                          📷<br>
                          <small>Không có</small>
                        </div>
                      </c:otherwise>
                    </c:choose>
                  </td>
                  <td>
                    <a href="${pageContext.request.contextPath}/DanhSachDienThoaiNCCServlet?maNCC=${dt.maNCC}" 
                       class="btn btn-primary btn-sm mb-1" title="Xem NCC">
                      🏢 NCC
                    </a><br>
                    <form method="post" action="${pageContext.request.contextPath}/QuanLyFormServlet" 
                          style="display: inline-block;" 
                          onsubmit="return confirmDelete('Bạn có chắc chắn muốn xóa điện thoại ${dt.maDT} - ${dt.tenDT} không?\n\nThao tác này không thể hoàn tác!')">
                      <input type="hidden" name="maDT" value="${dt.maDT}" />
                      <button type="submit" class="btn btn-danger btn-sm" title="Xóa điện thoại">
                        🗑️ Xóa
                      </button>
                    </form>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </c:if>
  
  <!-- Thông báo khi không có dữ liệu -->
  <c:if test="${allDienThoai == null || empty allDienThoai}">
    <div class="card">
      <div class="card-body text-center">
        <div class="alert alert-info">
          <h5>📭 Danh sách trống</h5>
          <p>Hiện tại không có điện thoại nào trong hệ thống.</p>
          <a href="${pageContext.request.contextPath}/DienThoaiFormServlet" 
             class="btn btn-success">
            ➕ Thêm điện thoại đầu tiên
          </a>
        </div>
      </div>
    </div>
  </c:if>
  
  <!-- Modal để hiển thị hình ảnh lớn -->
  <div class="modal fade" id="imageModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="imageModalTitle">Hình ảnh điện thoại</h5>
          <button type="button" class="close" data-dismiss="modal">
            <span>&times;</span>
          </button>
        </div>
        <div class="modal-body text-center">
          <img id="imageModalImage" src="" class="img-fluid" alt="Hình ảnh điện thoại" />
        </div>
      </div>
    </div>
  </div>
</div>

<script>
function showImageModal(imageSrc, title) {
  document.getElementById('imageModalImage').src = imageSrc;
  document.getElementById('imageModalTitle').textContent = title;
  
  // Show modal (bootstrap 4)
  document.getElementById('imageModal').style.display = 'block';
}

// Close modal when clicking outside
window.onclick = function(event) {
  const modal = document.getElementById('imageModal');
  if (event.target == modal) {
    modal.style.display = 'none';
  }
}

// Close modal when clicking X
document.querySelector('#imageModal .close').onclick = function() {
  document.getElementById('imageModal').style.display = 'none';
}
</script>

<style>
.modal {
  display: none;
  position: fixed;
  z-index: 1000;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0,0,0,0.5);
}

.modal-dialog {
  height: 80%;
  margin-top: 5%;
}

.modal-content {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.modal-body {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
  border-bottom: 1px solid #dee2e6;
}

.modal-title {
  margin: 0;
}

.close {
  background: none;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
  padding: 0;
  width: 30px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.text-truncate {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>

<jsp:include page="../layout/footer.jsp" />
