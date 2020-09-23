var userUrl;

const Menu = Mn.View.extend({
    tagName: 'div',
    className: 'menuClass',
    template: _.template(`<div id="profileMenu">Minimal</div><div id="friendsMenu">Friends</div>
        <div id="exitMenu"><button id="exitButton">Exit</button> </div>`),
    events: {
        'click @ui.exitButton' : 'exitMethod',
        'click @ui.friendsButton' : 'friendsMethod'
    },
    ui: {
        exitButton : '#exitButton',
        friendsButton : '#friendsMenu'
    },
    exitMethod(){
        console.log("Exit button pushed");
        $.ajax({
            url:   '/firstproject_war/rest/log/logout'  , //url страницы (action_ajax_form.php)
            type:     'POST', //метод отправки
            dataType: 'text', //формат данных
            data: "Check",  // Сеарилизуем объект
            success: function(response) { //Данные отправлены успешно
                console.log("Success AJAX exit");
                userUrl = undefined;
                socket.close;
                friendSocket.close;
                appRouter.navigate("auth", {trigger: true});
            },
            error: function() {
                console.log("Error AJAX exit");
            }
        });
    },
    friendsMethod(){
        appRouter.navigate("", {trigger: false});
        if (friendsContainer.isDestroyed()) {
            console.log("is destr");
            friendsContainer = new FriendsContainer();
        }
        if (profile.isDestroyed()) {
            console.log("Profile1 is attached  " + profile.isAttached() + ", is Destroyed " + profile.isDestroyed() +
                ", is Rendered " + profile.isRendered());
            profile = new Profile();
        }
        wrapper.showChildView('mainRegion', profile);
    }
});