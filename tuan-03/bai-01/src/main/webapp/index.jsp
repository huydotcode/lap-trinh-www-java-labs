<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #9ee1f5;
            padding: 20px;
        }

        form {
            background-color: #a8e5f5;
            padding: 20px;
            border-radius: 8px;
            width: 600px;
            margin: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        td {
            padding: 8px;
            vertical-align: top;
        }

        input[type="text"],
        input[type="email"],
        input[type="number"],
        textarea,
        select {
            width: 90%;
            padding: 5px;
        }

        textarea {
            resize: vertical;
            height: 60px;
        }

        .qualification {
            border: 2px solid #0000ff;
            padding: 8px;
        }

        .qualification table, .qualification td, .qualification th {
            border: 1px solid #0000ff;
        }

        .qualification td {
            padding: 4px;
        }

        .buttons {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<form action="registration-form" name="formDangKy" method="GET">
    <table>
        <tr>
            <td>First name</td>
            <td><input name="first-name" type="text" maxlength="30" placeholder="max 30 characters a-z and A-Z"></td>
        </tr>
        <tr>
            <td>Last name</td>
            <td><input name="last-name" type="text" maxlength="30" placeholder="max 30 characters a-z and A-Z"></td>
        </tr>
        <tr>
            <td>Date of birth</td>
            <td>
                <select name="dob-day">
                    <option value="">Day:</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>
                <select name="dob-month">
                    <option value="">Month:</option>
                    <option value="Jan">Jan</option>
                    <option value="Feb">Feb</option>
                    <option value="Mar">Mar</option>
                </select>
                <select name="dob-year">
                    <option value="">Year:</option>
                    <option value="1990">1990</option>
                    <option value="1991">1991</option>
                    <option value="1992">1992</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input name="email" type="email"></td>
        </tr>
        <tr>
            <td>Mobile number</td>
            <td><input name="mobile" type="text" maxlength="10" placeholder="10 digit number"></td>
        </tr>
        <tr>
            <td>Gender</td>
            <td>
                <input type="radio" name="gender" value="male"> Male
                <input type="radio" name="gender" value="female"> Female
            </td>
        </tr>
        <tr>
            <td>Address</td>
            <td><textarea name="address"></textarea></td>
        </tr>
        <tr>
            <td>City</td>
            <td><input name="city" type="text" maxlength="30" placeholder="max 30 characters a-z and A-Z"></td>
        </tr>
        <tr>
            <td>Pin code</td>
            <td><input name="pincode" type="text" maxlength="6" placeholder="6 digit number"></td>
        </tr>
        <tr>
            <td>State</td>
            <td><input name="state" type="text" maxlength="30" placeholder="max 30 characters a-z and A-Z"></td>
        </tr>
        <tr>
            <td>Country</td>
            <td><input name="country" type="text" value="India" readonly></td>
        </tr>
        <tr>
            <td>Hobbies</td>
            <td>
                <input type="checkbox" name="hobby_drawing" value="Drawing"> Drawing
                <input type="checkbox" name="hobby_singing" value="Singing"> Singing
                <input type="checkbox" name="hobby_dancing" value="Dancing"> Dancing
                <input type="checkbox" name="hobby_sketching" value="Sketching"> Sketching
                <input type="checkbox" name="hobby_others" value="Others"> Others
                <input type="text" name="hobby_other_text">
            </td>
        </tr>
    </table>

    <div class="qualification">
        <table width="100%">
            <tr>
                <th>Sl.No.</th>
                <th>Examination</th>
                <th>Board</th>
                <th>Percentage</th>
                <th>Year of Passing</th>
            </tr>
            <tr>
                <td>1</td>
                <td>Class X</td>
                <td><input name="board_x" type="text" maxlength="10"></td>
                <td><input name="percent_x" type="text" placeholder="upto 2 decimal"></td>
                <td><input name="year_x" type="text"></td>
            </tr>
            <tr>
                <td>2</td>
                <td>Class XII</td>
                <td><input name="board_xii" type="text" maxlength="10"></td>
                <td><input name="percent_xii" type="text" placeholder="upto 2 decimal"></td>
                <td><input name="year_xii" type="text"></td>
            </tr>
            <tr>
                <td>3</td>
                <td>Graduation</td>
                <td><input name="board_grad" type="text" maxlength="10"></td>
                <td><input name="percent_grad" type="text" placeholder="upto 2 decimal"></td>
                <td><input name="year_grad" type="text"></td>
            </tr>
            <tr>
                <td>4</td>
                <td>Masters</td>
                <td><input name="board_masters" type="text" maxlength="10"></td>
                <td><input name="percent_masters" type="text" placeholder="upto 2 decimal"></td>
                <td><input name="year_masters" type="text"></td>
            </tr>
        </table>
    </div>

    <table>
        <tr>
            <td>Course applies for</td>
            <td>
                <input type="radio" name="course" value="BCA"> BCA
                <input type="radio" name="course" value="B.Com"> B.Com
                <input type="radio" name="course" value="B.Sc"> B.Sc
                <input type="radio" name="course" value="B.A"> B.A
            </td>
        </tr>
    </table>

    <div class="buttons">
        <input type="submit" value="Submit">
        <input type="reset" value="Reset">
    </div>
</form>
</body>
</html>