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
        renderNewView(Auth);
    },
    registration: function() {
        renderNewView(Reg);
    },
    friends: function () {
        renderNewView(Profile);
    }
});