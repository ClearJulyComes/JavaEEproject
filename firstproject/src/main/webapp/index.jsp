<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8">
        <title>My application</title>
        <script src="./js/jquery-3.5.1.js"></script>
        <script src="./js/underscore.js"></script>
        <script src="./js/underscore.js.map"></script>
        <script src="./js/backbone.js"></script>
        <script src="https://github.com/marionettejs/backbone.radio.git"></script>
        <script src="./js/backbone.marionette.js"></script>
        <script type="text/javascript" src="./js/Models/FriendModel.js"></script>
        <script type="text/javascript" src="./js/Views/MenuView.js"></script>
        <script type="text/javascript" src="./js/Views/Wrapper.js"></script>
        <script type="text/javascript" src="./js/Views/AuthView.js"></script>
        <script type="text/javascript" src="./js/Views/RegView.js"></script>
        <script type="text/javascript" src="./js/Views/SearchFriendView.js"></script>
        <script type="text/javascript" src="./js/Views/FriendsContainerView.js"></script>
        <script type="text/javascript" src="./js/Views/ProfileView.js"></script>
        <script type="text/javascript" src="./js/Router/Router.js"></script>
    </head>
    <body>
        <div id="wrap">Ok lets start</div>
        <script type="text/template" id="authView">
                <form id="auth" method="post">
                    <table>
                        <tr>
                            <td>Login</td>
                            <td colspan="2">
                                <input type="text" name="login" placeholder="login">
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
                                <input type="text" name="login" placeholder="login" id="login">
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
                        <td>Add to your friend list</td>
                        <td colspan="2">
                            <input type="text" name="login" placeholder="login">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button id="addFriendButton" value="submit">Add</button>
                        </td>
                        <td></td>
                        <td>
                        </td>
                    </tr>
                </table>
            </form>
        </script>
        <script id="friendListContainer" type="text/template">Test <div id="friendListUL">yuiojk</div></script>
        <script type="text/javascript" src="./js/main.js"></script>
    </body>
</html>
