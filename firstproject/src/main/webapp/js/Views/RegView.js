const Reg = Mn.View.extend({
    initialize(){
        this.template = _.template($('#regView').html())
    },
    events: {
        'click @ui.toAuth' : 'toAuthorization',
        'click @ui.reg' : 'registration',
    }
    ,
    ui: {
        toAuth: '#toAuthButton',
        reg: '#regButton'
    },
    modelEvents:{
        'sync' : 'renderProfile',
        'error': 'errorReg'
    },
    toAuthorization(){
        router.navigate("auth", {trigger: true});
    },
    registration(){
        $('#reg').submit(function (e) {
            e.preventDefault();

            $.ajax({
                url:   '/firstproject_war/rest/log/reg'  , //url страницы (action_ajax_form.php)
                type:     'POST', //метод отправки
                dataType: 'application/json', //формат данных
                data: $('#reg').serialize(),  // Сеарилизуем объект
                success: function(response) { //Данные отправлены успешно
                    console.log("Success");
                    router.navigate("friends", {trigger: true});
                },
                error: function(response) { // Данные не отправлены
                    console.log("Error");
                    router.navigate("", {trigger: true});
                    alert('Something went wrong, try again');
                }
            })
        });
    }
});