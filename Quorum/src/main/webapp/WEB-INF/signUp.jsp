<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp" />
<body>
<jsp:include page="navbar.jsp" />
    <main>
        <h1>Sign Up</h1>
        <br>
        <table>
        <form action="signup.do">
            <tr>
                <th>
                    <label for="username">Username</label>
                </th>
                <td>
                    <input type="text" name="username" id="username" value="username">
                </td>
            </tr>   
            <tr>
                <th>
                    <label for="password">Password</label>
                </th>
                <td>
                    <input type="password" name="password" id="password" placeholder="password">
                </td>
            </tr>   
            <tr>
                <th>
                    <label for="firstName">First Name</label>
                </th>
                <td>
                    <input type="text" name="firstName" id="firstName" placeholder="First Name">
                </td>
            </tr>   
            <tr>
                <th>
                    <label for="lastName">Last Name</label>
                </th>
                <td>
                    <input type="text" name="lastName" id="lastName" placeholder="Last Name">
                </td>
            </tr>   
            <tr>
                <td>
                    <input type="submit" value="submit">
                </td>

            </tr>
        </form>
    </table>
    </main>
    <jsp:include page="footer.jsp" />
</body>
</html>
