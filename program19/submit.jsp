<%@ page import="java.sql.*" %>
<%
    String name = request.getParameter("name");
    String email = request.getParameter("email");

    // Simple validation
    if (name == null || name.trim().isEmpty() || email == null || !email.contains("@")) {
%>
        <h3>Invalid data. Please go back and try again.</h3>
<h3>Abhay Raj, 00976803122</h3>
<%
    } else {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/jsp_db", "root", "your_password");

            PreparedStatement ps = con.prepareStatement("INSERT INTO users(name, email) VALUES (?, ?)");
            ps.setString(1, name);
            ps.setString(2, email);
            int i = ps.executeUpdate();

            if(i > 0){
%>
                <h3>Data inserted successfully!</h3>
<h3>Abhay Raj, 00976803122</h3>
<%
            } else {
%>
                <h3>Insertion failed.</h3>
<%
            }

            con.close();
        } catch(Exception e){
%>
            <h3>Error: <%= e.getMessage() %></h3>
<%
        }
    }
%>

