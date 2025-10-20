<%@page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Update Employee</title>
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
      <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
          <!-- Header -->
          <div class="d-flex align-items-center mb-4">
            <a
              href="${pageContext.request.contextPath}/"
              class="btn btn-outline-secondary me-3"
            >
              <i class="bi bi-arrow-left"></i> Back
            </a>
            <h1 class="text-primary mb-0">
              <i class="bi bi-person-gear me-2"></i>Update Employee
            </h1>
          </div>

          <!-- Form Card -->
          <div class="card shadow">
            <div class="card-header bg-primary text-white">
              <h5 class="card-title mb-0">
                <i class="bi bi-person-gear me-2"></i>Update Employee
                Information
              </h5>
            </div>
            <div class="card-body">
              <%--@elvariable id="employee" type="java"--%>
              <form:form
                action="${pageContext.request.contextPath}/save-employee"
                modelAttribute="employee"
                method="post"
              >
                <div class="row">
                  <!-- First Name -->
                  <div class="col-md-6 mb-3">
                    <label for="firstName" class="form-label">
                      <i class="bi bi-person me-1"></i>First Name
                      <span class="text-danger">*</span>
                    </label>
                    <form:input
                      path="firstName"
                      id="firstName"
                      class="form-control"
                      placeholder="Enter first name"
                    />
                    <form:errors
                      path="firstName"
                      cssClass="text-danger small"
                    />
                  </div>

                  <!-- Last Name -->
                  <div class="col-md-6 mb-3">
                    <label for="lastName" class="form-label">
                      <i class="bi bi-person me-1"></i>Last Name
                      <span class="text-danger">*</span>
                    </label>
                    <form:input
                      path="lastName"
                      id="lastName"
                      class="form-control"
                      placeholder="Enter last name"
                    />
                    <form:errors path="lastName" cssClass="text-danger small" />
                  </div>
                </div>

                <!-- Gender -->
                <div class="mb-3">
                  <label class="form-label">
                    <i class="bi bi-gender-ambiguous me-1"></i>Gender
                  </label>
                  <div class="mt-2">
                    <div class="form-check form-check-inline">
                      <form:radiobutton
                        path="gender"
                        value="male"
                        id="male"
                        class="form-check-input"
                      />
                      <label class="form-check-label" for="male">
                        <i class="bi bi-gender-male text-primary me-1"></i>Male
                      </label>
                    </div>
                    <div class="form-check form-check-inline">
                      <form:radiobutton
                        path="gender"
                        value="female"
                        id="female"
                        class="form-check-input"
                      />
                      <label class="form-check-label" for="female">
                        <i class="bi bi-gender-female text-pink me-1"></i>Female
                      </label>
                    </div>
                  </div>
                  <form:errors path="gender" cssClass="text-danger small" />
                </div>

                <!-- Date of Birth -->
                <div class="mb-3">
                  <label for="dateOfBirth" class="form-label">
                    <i class="bi bi-calendar-date me-1"></i>Date of Birth
                  </label>
                  <form:input
                    path="dateOfBirth"
                    type="date"
                    id="dateOfBirth"
                    class="form-control"
                  />
                  <form:errors
                    path="dateOfBirth"
                    cssClass="text-danger small"
                  />
                </div>

                <!-- Email -->
                <div class="mb-3">
                  <label for="email" class="form-label">
                    <i class="bi bi-envelope me-1"></i>Email Address
                    <span class="text-danger">*</span>
                  </label>
                  <form:input
                    path="email"
                    type="email"
                    id="email"
                    class="form-control"
                    placeholder="Enter email address"
                  />
                  <form:errors path="email" cssClass="text-danger small" />
                </div>

                <!-- Phone -->
                <div class="mb-4">
                  <label for="phone" class="form-label">
                    <i class="bi bi-telephone me-1"></i>Phone Number
                    <span class="text-danger">*</span>
                  </label>
                  <form:input
                    path="phone"
                    type="tel"
                    id="phone"
                    class="form-control"
                    placeholder="Enter phone number"
                  />
                  <form:errors path="phone" cssClass="text-danger small" />
                </div>

                <!-- Action Buttons -->
                <div class="d-flex gap-2">
                  <button type="submit" class="btn btn-success flex-fill">
                    <i class="bi bi-check-circle me-1"></i>Update Employee
                  </button>
                  <a
                    href="${pageContext.request.contextPath}/"
                    class="btn btn-outline-secondary flex-fill"
                  >
                    <i class="bi bi-x-circle me-1"></i>Cancel
                  </a>
                </div>
              </form:form>
            </div>
          </div>

          <!-- Help Text -->
          <div class="mt-3">
            <small class="text-muted">
              <i class="bi bi-info-circle me-1"></i>
              Fields marked with <span class="text-danger">*</span> are
              required.
            </small>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Custom CSS -->
    <style>
      .text-pink {
        color: #e91e63 !important;
      }

      .form-control:focus {
        border-color: #0d6efd;
        box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, 0.25);
      }

      .card {
        border: none;
      }

      .form-check-input:checked {
        background-color: #0d6efd;
        border-color: #0d6efd;
      }
    </style>
  </body>
</html>
