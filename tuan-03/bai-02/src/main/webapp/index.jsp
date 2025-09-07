<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User Registration</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .container {
            width: 350px;
            margin: auto;
            border: 1px solid #ccc;
            padding: 20px;
            margin-top: 50px;
            border-radius: 5px;
        }
        h2 { text-align: center; }
        input, select {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            box-sizing: border-box;
        }
        .row { display: flex; gap: 5px; }
        .row input { flex: 1; }
        .gender { margin-top: 10px; display: flex; align-items: center }
        button {
            width: 100%;
            padding: 10px;
            background: #1877f2;
            color: white;
            border: none;
            border-radius: 4px;
        }
        button:hover {
            background: #145db2;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>User Registration Form</h2>
    <form action="register" method="post">
        <div class="row">
            <input type="text" name="firstName" placeholder="First Name" required />
            <input type="text" name="lastName" placeholder="Last Name" required />
        </div>
        <input type="email" name="email" placeholder="Your Email" required />
        <input type="email" name="reEmail" placeholder="Re-enter Email" required />
        <input type="password" name="password" placeholder="New Password" required />

        <label>Birthday</label><br/>
        <div class="row">
            <select name="month" required>
                <option value="">Month</option>
                <option value="1">Jan</option>
                <option value="2">Feb</option>
                <option value="3">Mar</option>
                <!-- thêm các tháng khác -->
            </select>
            <select name="day" required>
                <option value="">Day</option>
                <% for(int i=1; i<=31; i++){ %>
                <option value="<%= i %>"><%= i %></option>
                <% } %>
            </select>
            <select name="year" required>
                <option value="">Year</option>
                <% for(int y=2024; y>=1900; y--){ %>
                <option value="<%= y %>"><%= y %></option>
                <% } %>
            </select>
        </div>

        <div class="gender">
            <input style="width: fit-content; margin: 0 8px" type="radio" name="gender" value="Female" required /> Female
            <input style="width: fit-content; margin: 0 8px" type="radio" name="gender" value="Male" /> Male
        </div>

        <button type="submit">Sign Up</button>
    </form>
</div>
</body>
</html>
