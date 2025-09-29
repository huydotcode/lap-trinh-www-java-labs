<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp" />
<% request.setAttribute("pageTitle", "Danh Sách Điện Thoại"); %>

<div class="container">
  <div class="card">
    <div class="card-header">🔎 Tìm kiếm Nhà Cung Cấp</div>
    <div class="card-body">
      <form method="get" action="${pageContext.request.contextPath}/DanhSachDienThoaiNCCServlet">
        <div class="search-row">
          <div class="search-group">
            <label for="search" class="form-label">Từ khóa tìm kiếm:</label>
            <input type="text" id="search" name="search" class="form-control" 
                   placeholder="Tìm theo mã NCC, tên, địa chỉ, số điện thoại..."
                   value="${searchTerm}" />
          </div>
          <div class="search-group">
            <label for="maNCC" class="form-label">Chọn NCC:</label>
            <select id="maNCC" name="maNCC" class="form-control">
              <option value="">-- Tất cả NCC --</option>
              <c:forEach var="ncc" items="${allNhaCungCap}">
                <option value="${ncc.maNCC}" 
                        ${selectedNCC != null && selectedNCC == ncc.maNCC ? 'selected' : ''}>
                  ${ncc.tenNHacc}
                </option>
              </c:forEach>
            </select>
          </div>
          <div>
            <button type="submit" class="btn btn-primary">🔍 Tìm kiếm</button>
            <a href="${pageContext.request.contextPath}/DanhSachDienThoaiNCCServlet" 
               class="btn btn-secondary">📋 Hiện tất cả</a>
          </div>
        </div>
      </form>
    </div>
  </div>

  <!-- Hiển thị kết quả tìm kiếm NCC -->
  <c:if test="${searchResults != null}">
    <div class="card">
      <div class="card-header">🔍 Kết quả tìm kiếm Nhà Cung Cấp</div>
      <div class="card-body">
        <c:choose>
          <c:when test="${not empty searchResults}">
            <div class="table-responsive">
              <table class="table table-striped table-hover">
                <thead>
                  <tr>
                    <th>Mã NCC</th>
                    <th>Tên NCC</th>
                    <th>Địa chỉ</th>
                    <th>Số điện thoại</th>
                    <th>Thao tác</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="ncc" items="${searchResults}">
                    <tr>
                      <td>${ncc.maNCC}</td>
                      <td>${ncc.tenNHacc}</td>
                      <td>${ncc.diaChi}</td>
                      <td>${ncc.soDienThoai}</td>
                      <td>
                        <a href="${pageContext.request.contextPath}/DanhSachDienThoaiNCCServlet?maNCC=${ncc.maNCC}" 
                           class="btn btn-success btn-sm">📱 Xem điện thoại</a>
                      </td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
          </c:when>
          <c:otherwise>
            <div class="alert alert-warning">
              <strong>Không tìm thấy!</strong> Không có nhà cung cấp nào phù hợp với từ khóa tìm kiếm.
            </div>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </c:if>

  <!-- Hiển thị điện thoại theo NCC được chọn -->
  <c:if test="${dienThoaiByNCC != null}">
    <div class="card">
      <div class="card-header">
        📱 Điện thoại của ${selectedNhaCungCap != null ? selectedNhaCungCap.tenNHacc : 'NCC'}
      </div>
      <div class="card-body">
        <c:if test="${selectedNhaCungCap != null}">
          <div class="alert alert-info mb-3">
            <strong>Thông tin NCC:</strong><br>
            📍 Địa chỉ: ${selectedNhaCungCap.diaChi}<br>
            📞 Số điện thoại: ${selectedNhaCungCap.soDienThoai}
          </div>
        </c:if>
        
        <c:choose>
          <c:when test="${not empty dienThoaiByNCC}">
            <div class="product-grid">
              <c:forEach var="dt" items="${dienThoaiByNCC}">
                <div class="product-card">
                  <c:choose>
                    <c:when test="${not empty dt.hinhAnh}">
                      <img src="${pageContext.request.contextPath}/images/${dt.hinhAnh}"
                           class="product-card-image" alt="${dt.tenDT}" />
                    </c:when>
                    <c:otherwise>
                      <div class="product-card-image" 
                           style="background: linear-gradient(45deg, #f0f0f0, #e0e0e0); display: flex; align-items: center; justify-content: center; color: #666;">
                        📱 Không có ảnh
                      </div>
                    </c:otherwise>
                  </c:choose>
                  <div class="product-card-body">
                    <h5 class="mb-1">${dt.tenDT}</h5>
                    <p class="mb-1"><strong>Mã ĐT:</strong> ${dt.maDT}</p>
                    <p class="mb-1"><strong>Năm SX:</strong> ${dt.namSanXuat}</p>
                    <p class="mb-2"><strong>Cấu hình:</strong> ${dt.cauHinh}</p>
                  </div>
                </div>
              </c:forEach>
            </div>
          </c:when>
          <c:otherwise>
            <div class="alert alert-warning">
              <strong>Trống!</strong> Nhà cung cấp này chưa có điện thoại nào trong hệ thống.
            </div>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </c:if>

  <!-- Hiển thị tất cả điện thoại đã group theo NCC -->
  <c:if test="${dienThoaiGroupedByNCC != null}">
    <div class="card">
      <div class="card-header">📱 Danh sách điện thoại đã nhóm theo NCC</div>
      <div class="card-body">
        <c:choose>
          <c:when test="${not empty dienThoaiGroupedByNCC}">
            <c:forEach var="entry" items="${dienThoaiGroupedByNCC}">
              <div class="mb-3">
                <h4 class="text-primary">🏢 ${entry.key} (${entry.value.size()} sản phẩm)</h4>
                <div class="product-grid">
                  <c:forEach var="dt" items="${entry.value}">
                    <div class="product-card">
                      <c:choose>
                        <c:when test="${not empty dt.hinhAnh}">
                          <img src="${pageContext.request.contextPath}/images/${dt.hinhAnh}"
                               class="product-card-image" alt="${dt.tenDT}" />
                        </c:when>
                        <c:otherwise>
                          <div class="product-card-image" 
                               style="background: linear-gradient(45deg, #f0f0f0, #e0e0e0); display: flex; align-items: center; justify-content: center; color: #666;">
                            📱 Không có ảnh
                          </div>
                        </c:otherwise>
                      </c:choose>
                      <div class="product-card-body">
                        <h5 class="mb-1">${dt.tenDT}</h5>
                        <p class="mb-1"><strong>Mã ĐT:</strong> ${dt.maDT}</p>
                        <p class="mb-1"><strong>Năm SX:</strong> ${dt.namSanXuat}</p>
                        <p class="mb-2"><strong>Cấu hình:</strong> ${dt.cauHinh}</p>
                      </div>
                    </div>
                  </c:forEach>
                </div>
              </div>
              <hr>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <div class="alert alert-info">
              <strong>Thông báo:</strong> Hệ thống hiện tại chưa có dữ liệu điện thoại nào.
            </div>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </c:if>

  <!-- Hiển thị thông báo -->
  <c:if test="${message != null}">
    <div class="alert alert-info">
      📝 ${message}
    </div>
  </c:if>

  <!-- Hiển thị lỗi -->
  <c:if test="${error != null}">
    <div class="alert alert-danger">
      ⚠️ ${error}
    </div>
  </c:if>
</div>

<jsp:include page="../layout/footer.jsp" />
