    const Reg = Mn.View.extend({
        tagName: 'div',
        className: 'align-self-center',
        initialize() {
            this.template = _.template($('#regView').html())
        },
        events: {
            'click @ui.toAuth': 'toAuthorization',
            'click @ui.reg': 'registration',
        }
        ,
        ui: {
            toAuth: '#toAuthButton',
            reg: '#regButton'
        },
        modelEvents: {
            'sync': 'renderProfile',
            'error': 'errorReg'
        },
        toAuthorization() {
            appRouter.navigate("auth", {trigger: true});
        },
        registration() {
            $('#reg').submit(function (e) {
                e.preventDefault();

                $.ajax({
                    url: '/minimal/rest/log/reg',
                    type: 'POST',
                    dataType: 'application/json',
                    data: $('#reg').serialize().replace(/</g, "&lt;")
                        .replace(/>/g, "&gt;"),
                    success: function (response) {
                        userUrl = $("#loginReg").val();
                        fetchAll();
                        appRouter.navigate("friends", {trigger: true});
                    },
                    error: function (response) {
                        appRouter.navigate("", {trigger: true});
                        alert('Something went wrong, try again');
                    }
                })
            });
        }
    });