const Router = Backbone.Router.extend({

    routes: {
        "auth":                 "auth",
        "":         "registration",
        "friends":              "friends"
    },

    help: function() {
        console.log("help");
    },

    auth: function() {
        new Auth();
    },
    registration: function() {
        new Reg();
    },
    friends: function () {
        new Profile();
    }
});