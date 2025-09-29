<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>

<jsp:include page="../layout/header.jsp" />

<div class="container">
  <div class="card">
    <div class="card-header">➕ Thêm Điện Thoại Mới</div>
    <div class="card-body">
      <!-- Hiển thị thông báo thành công -->
      <c:if test="${success != null}">
        <div class="alert alert-success">✅ ${success}</div>
      </c:if>

      <!-- Hiển thị lỗi -->
      <c:if test="${error != null}">
        <div class="alert alert-danger">⚠️ ${error}</div>
      </c:if>

      <form
        action="${pageContext.request.contextPath}/DienThoaiFormServlet"
        method="post"
        enctype="multipart/form-data"
        id="dienThoaiForm"
      >
        <div class="form-group">
          <label for="maDT" class="form-label"
            >📱 Mã Điện Thoại (Bắt buộc)</label
          >
          <input
            type="text"
            id="maDT"
            name="maDT"
            class="form-control"
            placeholder="VD: DT001, DT002..."
            pattern="DT\d{3}"
            title="Mã điện thoại phải có định dạng DT001, DT002..."
            required
          />
          <div class="invalid-feedback">Mã điện thoại không được để trống!</div>
        </div>

        <div class="form-group">
          <label for="tenDT" class="form-label"
            >📱 Tên Điện Thoại (Bắt buộc)</label
          >
          <input
            type="text"
            id="tenDT"
            name="tenDT"
            class="form-control"
            placeholder="VD: Samsung Galaxy S24"
            required
          />
          <div class="invalid-feedback">
            Tên điện thoại không được để trống!
          </div>
        </div>

        <div class="form-group">
          <label for="namSanXuat" class="form-label"
            >🗓️ Năm Sản Xuất (Bắt buộc)</label
          >
          <input
            type="number"
            id="namSanXuat"
            name="namSanXuat"
            class="form-control"
            placeholder="VD: 2024"
            min="1900"
            max="2099"
            pattern="^(19|20)\d{2}$"
            title="Năm phải có 4 chữ số từ 1900-2099"
            required
          />
          <div class="invalid-feedback">
            Năm sản xuất phải là số có 4 chữ số từ 1900-2099!
          </div>
        </div>

        <div class="form-group">
          <label for="cauHinh" class="form-label"
            >⚙️ Thông Tin Cấu Hình (Bắt buộc)</label
          >
          <textarea
            id="cauHinh"
            name="cauHinh"
            class="form-control"
            rows="4"
            placeholder="VD: Chipset: Snapdragon 8 Gen 3, RAM: 8GB, Storage: 256GB, Camera: 200MP"
            maxlength="255"
            required
          ></textarea>
          <small class="text-muted">Tối đa 255 ký tự</small>
          <div class="invalid-feedback">
            Thông tin cấu hình không được để trống!
          </div>
        </div>

        <div class="form-group">
          <label for="maNCC" class="form-label"
            >🏢 Nhà Cung Cấp (Bắt buộc)</label
          >
          <select id="maNCC" name="maNCC" class="form-control" required>
            <option value="">-- Chọn nhà cung cấp --</option>
            <c:forEach var="ncc" items="${allNhaCungCap}">
              <option value="${ncc.maNCC}">
                ${ncc.tenNHacc} (${ncc.maNCC})
              </option>
            </c:forEach>
          </select>
          <div class="invalid-feedback">Vui lòng chọn nhà cung cấp!</div>
        </div>

        <div class="form-group">
          <label for="hinhAnh" class="form-label"
            >📷 Hình Ảnh (Không bắt buộc)</label
          >
          <input
            type="file"
            id="hinhAnh"
            name="hinhAnh"
            class="form-control"
            accept="image/png,image/jpg,image/jpeg"
            onchange="validateFileUpload(this)"
          />
          <small class="text-muted">
            Chỉ chấp nhận file PNG, JPG, JPEG. Kích thước tối đa 5MB.
          </small>
          <div class="invalid-feedback">File không hợp lệ!</div>
        </div>

        <div class="d-flex gap-2">
          <button
            type="submit"
            class="btn btn-success"
            onclick="return validateForm('dienThoaiForm')"
          >
            ➕ Thêm Điện Thoại
          </button>
          <button type="reset" class="btn btn-secondary">🔄 Làm Mới</button>
          <a
            href="${pageContext.request.contextPath}/DanhSachDienThoaiNCCServlet"
            class="btn btn-primary"
          >
            📃 Xem Danh Sách
          </a>
        </div>
      </form>
    </div>
  </div>

  <!-- Thông tin hướng dẫn -->
  <div class="card">
    <div class="card-header">📋 Hướng Dẫn Nhập Thông Tin</div>
    <div class="card-body">
      <div class="row">
        <div class="col-md-6">
          <h6>📝 Yêu Cầu Nhập Liệu:</h6>
          <ul>
            <li><strong>Mã ĐT:</strong> Định dạng DT001, DT002, DT003...</li>
            <li><strong>Tên ĐT:</strong> Không được để trống</li>
            <li><strong>Năm SX:</strong> 4 chữ số từ 1900-2099</li>
            <li><strong>Cấu hình:</strong> Tối đa 255 ký tự</li>
          </ul>
        </div>
        <div class="col-md-6">
          <h6>🚫 Quy Định Upload:</h6>
          <ul>
            <li><strong>File ảnh:</strong> Chỉ PNG, JPG, JPEG</li>
            <li><strong>Kích thước:</strong> Tối đa 5MB</li>
            <li><strong>Tên file:</strong> Sẽ tự động đổi tên</li>
            <li><strong>Lưu trữ:</strong> Trong thư mục /images/</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="../layout/footer.jsp" />
