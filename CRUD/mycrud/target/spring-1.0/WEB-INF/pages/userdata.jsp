<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Users Page</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        •tg td {
            front-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-cole: #ccc;
            color: #333;
            background-color: #fff
        }

        .tg th {
            front-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-stvle: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            color: #333;
            background-color: #f0f0f0;
        }
        .tg .tg-4eph{
            background-color: #f9f9f9;
        }

    </style>
</head>
<body>


<h1>User Details</h1>h1>

<table class="tg">
    <tr>
        <th width="80">ID</th>
        <th width="120">Name</th>
        <th width="120">Age</th>
        <th width="120">IsAdmin</th>
        <th width="200">Date</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
    <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.age}</td>
        <td>${user.admin}</td>
        <td>${user.date}</td>
        <td><a href="<c:url value='/edit/${user.id}'/>">Edit</a></td>
        <td><a href="<c:url value='/remove/${user.id}'/>">Delete</a></td>

        <td></td>

    </tr>

</table>
</body>
</html>
