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
  <form action="upload" method="post" enctype="multipart/form-data">
    <%--<input type="text" name="description" />--%>
    <input type="file" name="file" />
    <input type="submit" />
  </form>
</body>
</html>
