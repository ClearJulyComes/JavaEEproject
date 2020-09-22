    let appRouter;

    const Router = Backbone.Router.extend({

        routes: {
            "auth": "auth",
            "": "registration",
            "friends": "friends",
            "message/:friendLogin": "message"
        },

        help: function () {
            console.log("help");
        },

        auth: function () {
            if (userUrl !== undefined) {
                if (friendSocket === undefined) {
                    friendWebSocket();
                    messageWebSocket();
                }
                console.log("check +");
                appRouter.navigate("friends", {trigger: false});
                wrapper.showChildView('mainRegion', profile);
            } else {
                this.checkWebSocket(profile, "auth");
            }
        },
        registration: function () {
            if (profile.isDestroyed()) {
                profile = new Profile();
            }
            if (friendsContainer.isDestroyed()) {
                friendsContainer = new FriendsContainer();
            }
            if (userUrl !== undefined) {
                if (friendSocket === undefined) {
                    friendWebSocket();
                    messageWebSocket();
                }
                console.log("check +");
                appRouter.navigate("friends", {trigger: false});
                wrapper.showChildView('mainRegion', profile);
            } else {
                this.checkWebSocket(profile, new Reg());
            }
        },
        friends: function () {
            if (profile.isDestroyed()) {
                console.log("Profile1 is attached  " + profile.isAttached() + ", is Destroyed " + profile.isDestroyed() +
                    ", is Rendered " + profile.isRendered());
                profile = new Profile();
                console.log("Profile2 is attached  " + profile.isAttached() + ", is Destroyed " + profile.isDestroyed() +
                    ", is Rendered " + profile.isRendered());
            }
            if (friendsContainer.isDestroyed()) {
                friendsContainer = new FriendsContainer();
            }
            if (userUrl !== undefined) {
                if (friendSocket === undefined) {
                    friendWebSocket();
                    messageWebSocket();
                }
                console.log("Profile is attached  " + profile.isAttached() + ", is Destroyed " + profile.isDestroyed() +
                    ", is Rendered " + profile.isRendered());
                console.log("Wrapper is attached  " + wrapper.isAttached() + ", is Destroyed " + wrapper.isDestroyed() +
                    ", is Rendered " + wrapper.isRendered());
                wrapper.showChildView('mainRegion', profile);
            } else {
                this.checkWebSocket(profile);
            }
        },
        message(friendLogin) {
            if (chatView.isDestroyed()) {
                chatView = new ChatView();
            }
            chatView.setFriendLogin(friendLogin);
            console.log("friendLogin " + friendLogin);
            _.each(friendsContainer.model.toArray(), function (friend) {
                console.log("message each " + friend.toJSON().userLogin);
                if (friend.toJSON().userLogin === friendLogin) {
                    if (userUrl !== undefined) {
                        if (friendSocket === undefined) {
                            friendWebSocket();
                            messageWebSocket();
                        }
                        console.log("check +");
                        wrapper.showChildView('mainRegion', chatView);
                    } else {
                        this.checkWebSocket(chatView);
                    }
                }
            });
        },
        checkWebSocket(data, second) {

            $.ajax({
                url: '/firstproject_war/rest/log/check', //url страницы (action_ajax_form.php)
                type: 'POST', //метод отправки
                dataType: 'text', //формат данных
                data: "Check",  // Сеарилизуем объект
                success: function (response) { //Данные отправлены успешно
                    console.log("Success AJAX " + response);
                    if ($.trim(response) === "wrong") {
                        if (second === "auth") {
                            appRouter.navigate("auth", {trigger: false});
                            wrapper.showChildView('mainRegion', new Auth());
                        } else {
                            wrapper.showChildView('mainRegion', second);
                        }
                        console.log("wrong");
                    } else {
                        console.log("Okk");
                        userUrl = $.trim(response);
                        if (friendSocket === undefined) {
                            friendWebSocket();
                            messageWebSocket();
                        }
                        if (data === chatView) {
                            messages.fetch({
                                success: function (response) {
                                    console.log(response + " success fetch chat");
                                    wrapper.showChildView('mainRegion', data);
                                },
                                error: function () {
                                    console.log("error fetch")
                                }
                            });
                            friends.fetch();
                        } else {
                            friends.fetch({
                                success: function (response) {
                                    console.log(response + " success fetch list");
                                    wrapper.showChildView('mainRegion', data);
                                },
                                error: function () {
                                    console.log("error fetch")
                                }
                            });
                            messages.fetch();
                        }
                    }
                },
                error: function () { // Данные не отправлены
                    console.log("Error AJAX");
                    alert('Wrong user login');
                    appRouter.navigate("auth", {trigger: true});
                }
            });
        }
    });