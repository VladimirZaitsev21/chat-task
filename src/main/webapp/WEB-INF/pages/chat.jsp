
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style>
    <%@include file="css/chat.css"%>
</style>
<html>
<style>
    <%@include file="css/chat.css" %>
</style>
<head>
    <title>chat</title>
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="-1">
    <script src="https://code.jquery.com/jquery-2.2.4.min.js" type="text/javascript"></script>
</head>
<body>
<div class="general">
    <p class="hellow_sign" id="hellows">Здравствуйте, ${user.nick}!</p>
    <form action="front-controller" method="post">
        <input type="hidden" name="command" value="logoutCommand">
        <input type="submit" name="logout_button" value="Выйти">
    </form>

    <div class="first_level">
        <div class="text_area_div">
            <textarea id="allMessages" class="text_area_element" readonly>
                <c:forEach var="message" items="${dataProvider.getAllMessages()}">
                    <fmt:formatDate value="${message.dateStamp}" pattern="dd-MM-yyyy HH:mm"/><c:out
                        value=" ${message.author.nick}: ${message.messageContent}"/>
                </c:forEach>
            </textarea>
        </div>
        <div class="list_div">
            <c:choose>
                <c:when test="${user.role == 'USER'}">
                    Online users:
                    <c:forEach var="userItem" items="${dataProvider.getAllUsers()}">
                        <c:if test="${userItem.status == 'ONLINE'}">
                            <p><a class="li_nick">${userItem.nick}</a></p>
                        </c:if>
                    </c:forEach>
                </c:when>

                <c:when test="${user.role == 'ADMIN'}">
                    Online users:
                    <c:forEach var="userItem" items="${dataProvider.getAllUsers()}">
                        <c:if test="${userItem.status == 'ONLINE'}">
                            <p><a class="li_nick">${userItem.nick}</a></p>
                            <form action="front-controller" method="post">
                                <input type="hidden" name="command" value="banCommand"/>
                                <input type="hidden" name="banNick" value="${userItem.nick}"/>
                                <c:if test="${userItem.role != 'ADMIN'}">
                                    <button type="submit" name="button" value="Ban">BAN</button>
                                </c:if>
                            </form>
                            <form action="front-controller" method="post">
                                <input type="hidden" name="command" value="muteCommand"/>
                                <input type="hidden" name="muteNick" value="${userItem.nick}"/>
                                <c:if test="${userItem.role != 'ADMIN'}">
                                    <button type="submit" name="button" value="Mute">MUTE</button>
                                </c:if>
                            </form>
                        </c:if>
                    </c:forEach>

                    Offline users:
                    <c:forEach var="userItem" items="${dataProvider.getAllUsers()}">
                        <c:if test="${userItem.status == 'OFFLINE'}">
                            <p><a class="li_nick">${userItem.nick}</a></p>
                            <form action="front-controller" method="post">
                                <input type="hidden" name="command" value="banCommand"/>
                                <input type="hidden" name="banNick" value="${userItem.nick}"/>
                                <c:if test="${userItem.role != 'ADMIN'}">
                                    <button type="submit" name="button" value="Ban">BAN</button>
                                </c:if>
                            </form>
                            <form action="front-controller" method="post">
                                <input type="hidden" name="command" value="muteCommand"/>
                                <input type="hidden" name="muteNick" value="${userItem.nick}"/>
                                <c:if test="${userItem.role != 'ADMIN'}">
                                    <button type="submit" name="button" value="Mute">MUTE</button>
                                </c:if>
                            </form>
                        </c:if>
                    </c:forEach>

                    Banned users:
                    <c:forEach var="userItem" items="${dataProvider.getAllUsers()}">
                        <c:if test="${userItem.status == 'BANNED'}">
                            <p><a class="li_nick">${userItem.nick}</a></p>
                            <form action="front-controller" method="post">
                                <input type="hidden" name="command" value="unbanCommand"/>
                                <input type="hidden" name="unbanNick" value="${userItem.nick}"/>
                                <c:if test="${userItem.role != 'ADMIN'}">
                                    <button type="submit" name="button" value="unban">UNBAN</button>
                                </c:if>
                            </form>
                        </c:if>
                    </c:forEach>

                    Muted users:
                    <c:forEach var="userItem" items="${dataProvider.getAllUsers()}">
                        <c:if test="${userItem.status == 'MUTED'}">
                            <p><a class="li_nick">${userItem.nick}</a></p>
                            <form action="front-controller" method="post">
                                <input type="hidden" name="command" value="unmuteCommand"/>
                                <input type="hidden" name="unmuteNick" value="${userItem.nick}"/>
                                <c:if test="${userItem.role != 'ADMIN'}">
                                    <button type="submit" name="button" value="unmute">UNMUTE</button>
                                </c:if>
                            </form>
                        </c:if>
                    </c:forEach>
                </c:when>
            </c:choose>
        </div>
    </div>
    <div class="second_level">
        <form class="send_mes_form" name="sendMessage" action="front-controller" method="post" id="sendMsg">
            <input type="hidden" name="command" value="messageCommand"/>
            <input id="enter_message" class="input_message" type="textarea" name="messageText"
                   placeholder="Введите сообщение" required
                   maxlength="200"/>
            <button class="submit_button" type="submit" name="sendMessageButton">Отправить</button>
        </form>
    </div>

</div>
</body>
</html>

<script type="text/javascript">

    $('#document').ready(function () {
        $('#sendMsg').submit(function () {
            var message = $('#enter_message').val();
            $('#enter_message').reset();
            $.post('front-controller', {messageText: message}, function (response) {
                $("#allMessages").load("front-controller #allMessages");
            });
        });
        $('#enter_message').keypress(function (e) {
            if (e.which == 13) {
                var message = $('#enter_message').val();
                $('#enter_message').reset();
                $.post('front-controller', {messageText: message}, function (response) {
                    $("#allMessages").load("front-controller #allMessages");
                });
            }
        });

        function startTimer() {
            $('.text_area_div').load('chat .text_area_div', function () {
                setTimeout(startTimer, 3000);
            });
        }


        startTimer();
    });

</script>
