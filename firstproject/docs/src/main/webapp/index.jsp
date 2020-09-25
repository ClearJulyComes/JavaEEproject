<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Minimal</title>
        <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/favicon.png" type="image/x-icon">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
              crossorigin="anonymous">
        <link rel="stylesheet" href="css/styles.css">
        <script src="./js/jquery-3.5.1.js"></script>
        <script src="./js/underscore.js"></script>
        <script src="./js/underscore.js.map"></script>
        <script src="js/backbone.js"></script>
        <script src="https://github.com/marionettejs/backbone.radio.git"></script>
        <script src="./js/backbone.marionette.js"></script>
        <script type="text/javascript"  src="./js/Models/FriendModel.js"></script>
        <script type="text/javascript" src="./js/Models/MessageModel.js"></script>
    </head>
    <body>
        <div id="wrap" class="d-flex flex-column bg-light position-fixed h-100 w-100">Ok lets start</div>
        <script type="text/template" id="authView">
                <form id="auth" method="post">
                    <table>
                        <tr>
                            <td>Login</td>
                            <td colspan="2">
                                <input type="text" name="login" placeholder="login" id="loginAuth">
                            </td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td colspan="2">
                                <input type="text" name="password" placeholder="password">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button id="toRegButton">Registration</button>
                            </td>
                            <td>Login</td>
                            <td>
                                <button id="authButton">Ok</button>
                            </td>
                        </tr>
                    </table>
                </form>
        </script>
        <script type="text/template" id="regView">
                <form id="reg" method="post">
                    <table>
                        <tr>
                            <td>Login</td>
                            <td colspan="2">
                                <input type="text" name="login" placeholder="login" id="loginReg">
                            </td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td colspan="2">
                                <input type="text" name="password" placeholder="password" id="password">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button id="toAuthButton">Authorization</button>
                            </td>
                            <td>Registration</td>
                            <td>
                                <button id="regButton">Ok</button>
                            </td>
                        </tr>
                    </table>
                </form>
        </script>
        <script type="text/template" id="searchFriendView">
            <form id="addFriend" method="post">
                <table>
                    <tr>
                        <td>Find friend </td>
                        <td>
                            <input type="text" name="login" placeholder="login">
                        </td>
                        <td>
                            <button id="addFriendButton" value="submit">Add</button>
                        </td>
                    </tr>
                </table>
            </form>
        </script>
        <script id="textMsg" type="text/template">
            <input type="text" name="login" placeholder="Type your message">
            <button id="sendMessage"> Send </button>
        </script>
        <script id="chatTmpl" type="text/template">
            <div id="typeMessage"></div>
            <div id="messagesBox" class="d-flex flex-column"></div>
        </script>
        <script id="friendListContainer" type="text/template">Friends: <div id="friendsList"><table id="friendListUL"></table></div>
        </script>
        <script type="text/javascript" src="./js/Views/MenuView.js"></script>
        <script type="text/javascript" src="./js/Views/MenuUnname.js"></script>
        <script type="text/javascript" src="./js/Views/Wrapper.js"></script>
        <script type="text/javascript" src="./js/Views/AuthView.js"></script>
        <script type="text/javascript" src="./js/Views/RegView.js"></script>
        <script type="text/javascript" src="./js/Views/SearchFriendView.js"></script>
        <script type="text/javascript" src="./js/Views/FriendsContainerView.js"></script>
        <script type="text/javascript" src="./js/Views/TextMessageView.js"></script>
        <script type="text/javascript" src="./js/Views/ChatView.js"></script>
        <script type="text/javascript" src="./js/Views/ProfileView.js"></script>
        <script type="text/javascript" src="./js/Router/Router.js"></script>
        <script type="text/javascript" src="./js/webSocket.js"></script>
        <script type="text/javascript" src="./js/FriendWebSocket.js"></script>
        <script type="text/javascript" src="./js/app.js"></script>
    </body>
</html>
