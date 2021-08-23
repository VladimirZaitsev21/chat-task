
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    <%@include file="css/login.css"%>
</style>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="form_auth_block">
        <div class="form_auth_block_content">
            <p class="form_auth_block_head_text">Авторизация</p>
            <form action="front-controller" method="post" name="authorization">
                <label>Введите Ваш никнейм</label>
                <input type="hidden" name="command" value="loginCommand"/>
                <input class="input_login" type="text" name="nick"
                       placeholder="Никнейм" required minlength="3" maxlength="20" pattern="[A-Za-zА-Яа-яЁё0-9@.]+">
                <button class="form_auth_button" type="submit" name="form_auth_submit">Войти</button>
            </form>
        </div>
    </div>
</body>
</html>
