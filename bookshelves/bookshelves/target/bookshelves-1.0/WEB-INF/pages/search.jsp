<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>

<head>

    <title>Search books</title>


    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 18px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>

    <style type="text/css">
        a.link1 { font-size: 20px; color: black;}
    </style>

</head>
<body>


<!-- Возврат на начальную страницу -->
<a href="<c:url value='/books'/>">Back to bookshelves</a>

<br/>
<br/>

<a href="/search">Reset search</a>

<br/>
<br/>

<h1>Search options</h1>

<br/>
<br/>

<c:url var="saveUrl" value="/searching"/>

<form:form action="${saveUrl}"
           commandName="bookFilter">
    <table>
        <tr>
            <td><form:label path="id">User id:</form:label></td>
            <td><form:label path="title">Title:</form:label></td>
            <td><form:label path="description">Description:</form:label></td>
            <td><form:label path="author">Author:</form:label></td>
            <td><form:label path="isbn">Isbn:</form:label></td>
            <td><form:label path="printYear">Print Year:</form:label></td>


        </tr>

        <tr>
            <td><form:input path="id"/></td>
            <td><form:input path="title"/></td>
            <td><form:input path="description"/></td>
            <td><form:input path="author"/></td>
            <td><form:input path="isbn"/></td>
            <td><form:input path="printYear"/></td>


        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="Find"/>
            </td>
        </tr>

    </table>
</form:form>

<form:form action="${saveUrl}"
           commandName="bookFilter">
    <table>
        <tr>

            <td><form:label path="readAlready">Is read Already:</form:label></td>


        </tr>

        <tr>

            <td><form:input path="readAlready"/></td>


        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="Find by ReadAlready"/>
            </td>
        </tr>

    </table>
</form:form>

<h1>Found books</h1>


<c:if test="${!empty listBooks}">
    <table class="tg"> <!-- Элемент <table> создает таблицу-->
        <tr> <!--Элемент <tr> создает строку таблицы.-->
            <th width="80">ID</th>
            <th width="120">Title</th>
            <th width="120">Description</th>
            <th width="120">Author</th>
            <th width="80">Isbn</th>
            <th width="80">PrintYear</th>
            <th width="140" colspan="2">readAlready</th>
            <th width="60">Update</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listBooks}" var="book">
            <tr>
                <td align="center">${book.id}</td>
                <!--Элемент <td> создает ячейку данных. Этот тег должен находится внутри элемента <tr>-->
                <td><a href="/bookdata/${book.id}" target="_blank">${book.title}</a></td>
                <td>${book.description}</td>
                <td>${book.author}</td>
                <td>${book.isbn}</td>
                <td align="center">${book.printYear}</td>
                <td width="50">  ${book.readAlready}</td>
                <td align="center" valign="middle">
                    <c:if test="${book.readAlready==false}">
                        <c:url var="readAlready" value="readAlready/${book.id}"/>
                        <form:form action="${readAlready}" commandName="book">
                            <input type="submit" value="<spring:message text="Read"/>"/>
                        </form:form>
                    </c:if>
                </td>
                <td align="center"><a href="<c:url value='/edit/${book.id}'/>">Update</a></td>
                <td align="center"><a href="<c:url value='/remove/${book.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<br/>






</body>
</html>
