let appRouter;

const Router = Backbone.Router.extend({

    routes: {
        "auth":                 "auth",
        "":         "registration",
        "friends":              "friends",
        "message/:friendLogin": "message"
    },

    help: function() {
        console.log("help");
    },

    auth: function() {
        myApp.getView().showChildView('mainRegion', new Auth());
    },
    registration: function() {
        myApp.getView().showChildView('mainRegion', new Reg());
    },
    friends: function () {
        myApp.getView().showChildView('mainRegion', profile);
    },
    message(friendLogin){
        console.log("message view");
        chatView.setFriendLogin(friendLogin);
        chatView.fetchMessages();
        myApp.getView().showChildView('mainRegion', chatView);
    }
});