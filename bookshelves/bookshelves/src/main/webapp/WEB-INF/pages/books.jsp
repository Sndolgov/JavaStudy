<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<!-- открывающий тег, который прекращает все недомолвки и прямо говорит браузеру, что все, что между ним и закрывающим тегом </html>, является HTML документом -->
<head>  <!-- Элемент <head> располагается перед элементом <body>. Он содержит информацию о веб-странице. Это и есть заголовок документа HTML.
Информация, расположенная в элементе <head>, не отображается в окне браузера. -->

    <title>Books Page</title>
    <!-- текст, помещенный между тегами <title> и </title>, стал как бы названием веб-страницы -->

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
<!-- Все что находится между <body> и </body> является основным содержимым веб-страницы и выводится в окне браузера -->
<!-- -->

<!-- Возврат на начальную страницу -->
<a href="../../index.jsp">Back to main menu</a> <!-- Чтобы определить ссылку, используется тег <a>, однако этому тегу требуется направление ссылки.
Направление ссылки задается в атрибуте href тега <a>. Ссылка не обязательно должна ссылаться на другой HTML файл. Она может ссылаться на любой файл в сети.
Cсылка может отсылать пользователя к другой части той же самой страницы например, "<h2 id="moss">Мох</h2>", а затем создать ссылку на этот тег, например, следующим образом: "<a href="#moss">Перейти к моху</a>-->

<!-- У тегов также могут быть атрибуты. Атрибуты – это определенная дополнительная информация.
Атрибуты определяются в открывающем теге, а их значения всегда заключаются в кавычки.
Все это выглядит следующим образом: <тег атрибут="значение">контент</тег> -->

<br/> <!-- тег разрыва строки закрывает сам себя-->
<br/>

<a href="/search">Search books</a>
<br/>
<br/>


<h1>Bookshelve</h1>
<!--Это теги <h1>, <h2>, <h3>, <h4>, <h5> и <h6>. Тег <h1> – это истинный император всех заголовков, а тег <h6> – самый низший из них-->

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
        <c:forEach items="${pageBooks}" var="book">
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

<c:if test="${listBooks.size() > -1}">
    <c:forEach begin="1" end="${count}" var="val">
        <c:url var="pageURL" value="http://localhost:8080/bookspage?numpage=${val}"/>

        <c:if test="${val!=count && val!=thisNumber}">
            <a href="${pageURL}" >${val}</a> -
        </c:if>
        <c:if test="${val==count && val!=thisNumber}">
            <a href="${pageURL}">${val}</a>
        </c:if>
        <c:if test="${val==thisNumber}">
            <a href="${pageURL}"class="link1">${val}</a> -
        </c:if>

    </c:forEach>
</c:if>
<br/>


<h1>Creat a Book</h1>

<c:url var="addAction" value="/books/add"/>

<form:form action="${addAction}" commandName="book">
    <table>
        <c:if test="${!empty book.title}"> <!-- -->
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="title">
                    <spring:message text="Title*"/>
                </form:label>
            </td>
            <td>
                <form:input path="title"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="description">
                    <spring:message text="Description*"/>
                </form:label>
            </td>
            <td>
                <form:input path="description"/>
            </td>
        </tr>

            <tr>
                <td>
                    <form:label path="author">
                        <spring:message text="Author*"/>
                    </form:label>
                </td>
                <td>
                    <c:if test="${empty book.title}">
                    <form:input path="author"/>
                    </c:if>
                    <c:if test="${!empty book.title}">
                        <form:input path="author" readonly="true"  disabled="true"/>
                        <form:hidden path="author"/>
                    </c:if>
                </td>
            </tr>

        <tr>
            <td>
                <form:label path="isbn">
                    <spring:message text="Isbn*"/>
                </form:label>
            </td>
            <td>
                <form:input path="isbn"/>
            </td>
        </tr>


        <tr>
            <td>
                <form:label path="printYear">
                    <spring:message text="PrintYear*"/>
                </form:label>
            </td>
            <td>
                <form:input path="printYear"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="readAlready">
                    <spring:message text="ReadAlready"/>
                </form:label>
            </td>
            <td>
                    <form:input path="readAlready" readonly="true"  disabled="true"/>
                    <form:hidden path="readAlready"/>
            </td>
        </tr>

        <tr>
            <td colspan="1">
                <c:if test="${!empty book.title}">
                    <input type="submit"
                           value="<spring:message text="Update Book"/>"/> <!-- submit – Кнопка для отправки данных формы на сервер. -->
                </c:if>
                <c:if test="${empty book.title}">
                    <input type="submit"
                           value="<spring:message text="Creat Book"/>"/>
                </c:if>
            </td>
        </tr>
        <br/>

    </table>
    <table>
        <tr>
            <p><font size="2">* - this field is required</font></p>

        <tr>
    </table>
</form:form>



</body>
</html>
