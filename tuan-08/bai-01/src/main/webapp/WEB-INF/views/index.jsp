<%@page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Employee Management</title>
    <!-- Bootstrap CSS -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <!-- Bootstrap Icons -->
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css"
    />
  </head>
  <body class="bg-light">
    <div class="container mt-4">
      <!-- Header -->
      <div class="row mb-4">
        <div class="col-12">
          <div class="d-flex justify-content-between align-items-center">
            <h1 class="text-primary">
              <i class="bi bi-people-fill me-2"></i>Employee Management
            </h1>
            <a
              href="${pageContext.request.contextPath}/add-employee"
              class="btn btn-success"
            >
              <i class="bi bi-person-plus me-1"></i>Add New Employee
            </a>
          </div>
        </div>
      </div>

      <!-- Employee Table -->
      <div class="row">
        <div class="col-12">
          <div class="card shadow">
            <div class="card-header bg-primary text-white">
              <h5 class="card-title mb-0">
                <i class="bi bi-list-ul me-2"></i>Employee List
              </h5>
            </div>
            <div class="card-body p-0">
              <div class="table-responsive">
                <table class="table table-hover mb-0">
                  <thead class="table-light">
                    <tr>
                      <th scope="col">#</th>
                      <th scope="col">First Name</th>
                      <th scope="col">Last Name</th>
                      <th scope="col">Gender</th>
                      <th scope="col">Date of Birth</th>
                      <th scope="col">Email</th>
                      <th scope="col">Phone</th>
                      <th scope="col" class="text-center">Actions</th>
                    </tr>
                  </thead>
                  <tbody>
                    <jsp:useBean
                      id="employees"
                      scope="request"
                      type="java.util.List"
                    />
                    <c:choose>
                      <c:when test="${empty employees}">
                        <tr>
                          <td colspan="8" class="text-center text-muted py-4">
                            <i class="bi bi-inbox display-4 d-block mb-2"></i>
                            No employees found.
                            <a
                              href="${pageContext.request.contextPath}/add-employee"
                              class="text-decoration-none"
                              >Add the first employee</a
                            >
                          </td>
                        </tr>
                      </c:when>
                      <c:otherwise>
                        <c:forEach var="employee" items="${employees}">
                          <tr>
                            <td>
                              <span class="badge bg-secondary"
                                >${employee.id}</span
                              >
                            </td>
                            <td><strong>${employee.firstName}</strong></td>
                            <td><strong>${employee.lastName}</strong></td>
                            <td>
                              <span
                                class="badge ${employee.gender == 'male' ? 'bg-primary' : 'bg-pink'}"
                              >
                                <i
                                  class="bi bi-${employee.gender == 'male' ? 'gender-male' : 'gender-female'} me-1"
                                ></i>
                                ${employee.gender}
                              </span>
                            </td>
                            <td>${employee.dateOfBirth}</td>
                            <td>
                              <a
                                href="mailto:${employee.email}"
                                class="text-decoration-none"
                              >
                                <i class="bi bi-envelope me-1"></i
                                >${employee.email}
                              </a>
                            </td>
                            <td>
                              <a
                                href="tel:${employee.phone}"
                                class="text-decoration-none"
                              >
                                <i class="bi bi-telephone me-1"></i
                                >${employee.phone}
                              </a>
                            </td>
                            <td class="text-center">
                              <div class="btn-group" role="group">
                                <a
                                  href="${pageContext.request.contextPath}/update-employee?employee_id=${employee.id}"
                                  class="btn btn-outline-primary btn-sm"
                                >
                                  <i class="bi bi-pencil-square"></i> Edit
                                </a>
                                <button
                                  type="button"
                                  class="btn btn-outline-danger btn-sm"
                                  onclick="confirmDelete(${employee.id}, '${employee.firstName} ${employee.lastName}')"
                                >
                                  <i class="bi bi-trash"></i> Delete
                                </button>
                              </div>
                            </td>
                          </tr>
                        </c:forEach>
                      </c:otherwise>
                    </c:choose>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Custom CSS -->
    <style>
      .bg-pink {
        background-color: #e91e63 !important;
      }

      .table-hover tbody tr:hover {
        background-color: rgba(0, 123, 255, 0.1);
      }

      .card {
        border: none;
      }
    </style>

    <!-- JavaScript for delete confirmation -->
    <script>
      function confirmDelete(id, name) {
        if (
          confirm("Are you sure you want to delete employee: " + name + "?")
        ) {
          // Redirect to delete endpoint
          document.location.href =
            "${pageContext.request.contextPath}/delete-employee?employee_id=" +
            id;
        }
      }
    </script>
  </body>
</html>
