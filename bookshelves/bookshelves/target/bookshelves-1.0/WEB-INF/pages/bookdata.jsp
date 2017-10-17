<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Books Page</title>

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


<h1>Book Details</h1>

<table class="tg">
    <tr>
        <th width="80">ID</th>
        <th width="120">Title</th>
        <th width="120">Description</th>
        <th width="120">Author</th>
        <th width="80">Isbn</th>
        <th width="80">PrintYear</th>
        <th width="60">readAlready</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
    <tr>
        <td align="center">${book.id}</td>
        <td><a href="/bookdata/${book.id}" target="_blank">${book.title}</a></td>
        <td>${book.description}</td>
        <td>${book.author}</td>
        <td>${book.isbn}</td>
        <td align="center">${book.printYear}</td>
        <td align="center">${book.readAlready}</td>
        <td><a href="<c:url value='/edit/${book.id}'/>">Edit</a></td>
        <td><a href="<c:url value='/remove/${book.id}'/>">Delete</a></td>

        <td></td>

    </tr>

</table>
</body>
</html>
