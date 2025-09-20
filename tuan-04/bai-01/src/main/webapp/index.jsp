<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Shopping Cart Application</title>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.3/css/bootstrap.min.css"
      integrity="sha512-SbiR/eusphKoMVVXysTKG/7VseWii+Y3FdHrt0EpKgpToZeemhqHeZeLWLhJutz/2ut2Vw1uQEj2MbRF+TVBUA=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
  </head>
  <body>
    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card">
            <div class="card-body text-center">
              <h1 class="card-title">🛒 Shopping Cart Application</h1>
              <p class="card-text">Welcome to our online shopping platform!</p>
              <hr />
              <div class="d-grid gap-2">
                <a
                  href="<%=request.getContextPath()%>/products"
                  class="btn btn-primary btn-lg"
                >
                  🛍️ View Products
                </a>
                <a
                  href="<%=request.getContextPath()%>/cart"
                  class="btn btn-success btn-lg"
                >
                  🛒 View Cart
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
