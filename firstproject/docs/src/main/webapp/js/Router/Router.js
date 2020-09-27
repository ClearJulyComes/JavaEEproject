    let appRouter;

    const Router = Backbone.Router.extend({

        routes: {
            "auth": "auth",
            "": "registration",
            "friends": "friends",
            "message/:friendLogin": "message"
        },
        auth: function () {
            if (userUrl !== undefined) {
                if (friendSocket === undefined) {
                    friendWebSocket();
                    messageWebSocket();
                }
                appRouter.navigate("friends", {trigger: false});
                wrapper.showChildView('menuRegion', new Menu());
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
                appRouter.navigate("friends", {trigger: false});
                wrapper.showChildView('menuRegion', new Menu());
                wrapper.showChildView('mainRegion', profile);
            } else {
                this.checkWebSocket(profile, new Reg());
            }
        },
        friends: function () {
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
                wrapper.showChildView('menuRegion', new Menu());
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
            if (userUrl !== undefined) {
                if (friendSocket === undefined) {
                    friendWebSocket();
                    messageWebSocket();
                }
                if(friends.findWhere({userLogin: chatView.friendLogin}).toJSON().userLogin===chatView.friendLogin) {
                    chatView.onRender();
                    wrapper.showChildView('menuRegion', new Menu());
                    wrapper.showChildView('mainRegion', chatView);
                }
            } else {
                this.checkWebSocket(chatView);
            }
        },
        checkWebSocket(data, second) {

            $.ajax({
                url: '/minimal/rest/log/check',
                type: 'POST',
                dataType: 'text',
                data: "Check",
                success: function (response) {
                    if ($.trim(response) === "wrong") {
                        if (second === "auth") {
                            appRouter.navigate("auth", {trigger: false});
                            wrapper.showChildView('menuRegion', new MenuUnname());
                            wrapper.showChildView('mainRegion', new Auth());
                        } else {
                            wrapper.showChildView('menuRegion', new MenuUnname());
                            wrapper.showChildView('mainRegion', second);
                        }
                    } else {
                        userUrl = $.trim(response);
                        if (friendSocket === undefined) {
                            friendWebSocket();
                            messageWebSocket();
                        }
                        if (data === chatView) {
                            messages.fetch({
                                success: function (response) {
                                    messages.sortByField('messageId');
                                    if(friends.findWhere({userLogin: chatView.friendLogin}).toJSON().userLogin ===
                                        chatView.friendLogin){
                                        chatView.onRender();
                                        wrapper.showChildView('menuRegion', new Menu());
                                        wrapper.showChildView('mainRegion', data);
                                    }else {
                                        appRouter.navigate("auth", {trigger: false});
                                        wrapper.showChildView('menuRegion', new MenuUnname());
                                        wrapper.showChildView('mainRegion', new Auth());
                                    }
                                },
                                error: function () {
                                }
                            });
                            friends.fetch();
                        } else {
                            friends.fetch({
                                success: function (response) {
                                    wrapper.showChildView('menuRegion', new Menu());
                                    wrapper.showChildView('mainRegion', data);
                                },
                                error: function () {
                                }
                            });
                            messages.fetch({
                                success:function () {
                                    messages.sortByField('messageId');
                                }
                            });
                        }
                    }
                },
                error: function () {
                    alert('Wrong user login');
                    appRouter.navigate("auth", {trigger: true});
                }
            });
        }
    });
    function fetchAll(){
        messages.fetch({
            success: function (response) {
                console.log("Success message fetch");
                messages.sortByField('messageId');
                chatView.onRender();
            },
            error: function () {
            }
        });
        friends.fetch({
            success: function (response) {
                friendsContainer.onRender();
            },
            error: function () {
            }
        });
    }