var userUrl;

const Menu = Mn.View.extend({
    template: _.template(`<ul class="navbar-nav">
            <li class="nav-item ml-2"><img src="/firstproject_war/favicon.png" 
            class="img-fluid" width="50" height="50" alt="Responsive image"></li>
            <li class="navbar-brand mr-3 ml-2" id="profileMenu">Minimal</li>
            <li class="nav-item ml-2" id="friendsMenu">Friends</li>
            <li class="nav-item ml-2"  id="exitMenu"><button id="exitButton" class="btn btn-light">Exit</button></li>
        </ul>`),
    events: {
        'click @ui.exitButton' : 'exitMethod',
        'click @ui.friendsButton' : 'friendsMethod'
    },
    ui: {
        exitButton : '#exitButton',
        friendsButton : '#friendsMenu'
    },
    exitMethod(){
        $.ajax({
            url:   '/minimal/rest/log/logout'  ,
            type:     'POST',
            dataType: 'text',
            data: "Check",
            success: function(response) {
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
            friendsContainer = new FriendsContainer();
        }
        if (profile.isDestroyed()) {
            profile = new Profile();
        }
        wrapper.showChildView('mainRegion', profile);
    }
});