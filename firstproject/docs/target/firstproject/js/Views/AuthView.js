const Auth = Mn.View.extend({
    tagName: 'div',
    className: 'align-self-center',
    initialize() {
        this.template = _.template($('#authView').html())
    },
    events: {
        'click @ui.toReg': 'toRegistration',
        'click @ui.auth': 'authorization',
    }
    ,
    ui: {
        toReg: '#toRegButton',
        auth: '#authButton'
    }
    ,
    toRegistration() {
        appRouter.navigate("", {trigger: true});
    },
    authorization() {
        $('#auth').submit(function (e) {
            e.preventDefault();

            $.ajax({
                url: '/firstproject_war/rest/log/auth',
                type: 'POST',
                dataType: 'text',
                data: $('#auth').serialize().replace(/</g, "&lt;")
                    .replace(/>/g, "&gt;"),
                success: function (response) {
                    userUrl = $("#loginAuth").val();
                    if ($.trim(response) === "fine") {
                        fetchAll();
                        appRouter.navigate("friends", {trigger: true});
                    } else {
                        alert('Wrong password');
                        myApp.getView().showChildView('mainRegion', new Auth());
                    }
                },
                error: function () {
                    alert('Wrong user login');
                    myApp.getView().showChildView('mainRegion', new Auth());
                }
            })
        });
    }
});