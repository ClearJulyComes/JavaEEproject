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
        renderWrapperNewView(Auth);
    },
    registration: function() {
        renderWrapperNewView(Reg);
    },
    friends: function () {
        renderProfileView();
    }
});