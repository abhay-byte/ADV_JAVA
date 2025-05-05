<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    boolean valid = true;

    if(name == null || name.trim().equals("")) {
        out.println("Name is required.<br>");
        valid = false;
    }

    if(email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
        out.println("Invalid email format.<br>");
        valid = false;
    }

    if(valid) {
%>
        <h2>Form Submitted Successfully!</h2>
        <p>Name: <%= name %></p>
        <p>Email: <%= email %></p>
<h3>Abhay Raj, 00976803122</h3>
<%
    }
%>