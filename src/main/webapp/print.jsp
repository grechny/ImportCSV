<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ImportCSV</title>
</head>
<body>
  <ul>
    <li>
      <a href="/">Меню</a>
    </li>
    <li>
      <a href="/print">Просмотр</a>
    </li>
    <li>
      <a href="/upload">Импорт</a>
    </li>
  </ul>
  <table align="center" border="1" cellpadding="1" cellspacing="1" style="width: 500px">
    <caption>Contacts</caption>
    <tbody>
    <tr>
      <td align="center"><b><a href="/print?orderBy=login">login</a></b></td>
      <td align="center"><b><a href="/print?orderBy=name">name</a></b></td>
      <td align="center"><b><a href="/print?orderBy=surname">surname</a></b></td>
      <td align="center"><b><a href="/print?orderBy=email">email</a></b></td>
      <td align="center"><b><a href="/print?orderBy=phone_number">phone number</a></b></td>
    </tr>
    <%--@elvariable id="contactList" type="java.util.List"--%>
    <c:forEach var="contact" items="${contactList}">
      <tr>
        <td><c:out value="${contact.getLogin()}"/></td>
        <td><c:out value="${contact.getName()}"/></td>
        <td><c:out value="${contact.getSurname()}"/></td>
        <td><c:out value="${contact.getEmail()}"/></td>
        <td><c:out value="${contact.getPhoneNumber()}"/></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <p align="center">
  <%--@elvariable id="start" type="java.lang.Integer"--%>
  <%--@elvariable id="count" type="java.lang.Integer"--%>
  <%--@elvariable id="countAll" type="java.lang.Integer"--%>
  <%--@elvariable id="orderBy" type="java.lang.String"--%>
    <c:choose>
      <c:when test="${start == 0}">
        <input name="prev" type="button" value="prev" disabled>
      </c:when>
      <%--@elvariable id="count" type="int"--%>
      <c:when test="${start < count}">
        <a href="/print?count=${count}&orderBy=${orderBy}"><input name="prev" type="button" value="prev"></a>
      </c:when>
      <c:otherwise>
        <a href="/print?start=<c:out value="${start - count}"/>&count=${count}&orderBy=${orderBy}"><input name="prev" type="button" value="prev"></a>
      </c:otherwise>
    </c:choose>
    <c:choose>
      <c:when test="${start + count < countAll}">
        <a href="/print?start=<c:out value="${start + count}"/>&count=${count}&orderBy=${orderBy}"><input name="next" type="button" value="next"></a>
      </c:when>
      <c:otherwise>
        <input name="next" type="button" value="next" disabled>
      </c:otherwise>
    </c:choose>
  </p>
</body>
</html>
