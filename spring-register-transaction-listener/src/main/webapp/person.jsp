<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="ru.alex.svalka.model.Person"%>
 
<jsp:useBean id="personDao" type="ru.alex.svalka.service.PersonDao" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Сотрудники</title>
    </head>
 
    <body>
        <form method="POST" action="person.html">
            Имя: <input type="text" name="name" />
            Фамилия: <input type="text" name="family" />
            <input type="submit" value="Добавить сотрудника" />
        </form>
 
        <hr><ol> 
        <% for (Person person : personDao.getAllPersons()) { %>
            <li> <%= person %> </li>
        <% } %>
        </ol><hr>
     </body>
 </html>