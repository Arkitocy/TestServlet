<%--
  Created by IntelliJ IDEA.
  User: 50304
  Date: 2019/7/20
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ page import="Service.ProductService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Bean.Product" %>
<html>
<head>
    <title>产品列表</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body style="height:1500px">

<%String username = (String) session.getAttribute("username");%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark ">
    <a class="navbar-brand" href="#">Logo
    </a>
    <ul class="navbar-nav" style="margin-left: 1200px">
        <li><p class="navbar-brand">欢迎用户：<%=username%></p>
        </li>
        <li class="nav-item"><a class="nav-link" href="login.html">退出</a>
        </li>
    </ul>

</nav>
<div class="table-responsive">
<table class="table table-bordered table-hover table-bordered table-sm">
    <h2>产品列表</h2>
    <thead>
    <tr>
        <th>编号</th>
        <th>名字</th>
        <th>分类</th>
        <th>生产时间</th>
        <th>保质期</th>
        <th>价格</th>
    </tr>
    </thead>
    <tbody>
    <%
        ProductService ps = new ProductService();
        ArrayList<Product> ls = ps.showAll();
        for (int i = 0; i < ls.size(); i++) {
            Product p = ls.get(i);
    %>
    <tr>
        <td><%=p.getId()%>
        </td>
        <td><%=p.getName()%>
        </td>
        <td><%=p.getCategory()%>
        </td>
        <td><%=p.getProductiondate()%>
        </td>
        <td><%=p.getOutdate()%>
        </td>
        <td><%=p.getPrice()%>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>
</div>
</body>
</html>
