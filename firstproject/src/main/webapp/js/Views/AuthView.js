const Auth = Mn.View.extend({
    el: $('#myArea'),
    initialize(){
        this.template = _.template($('#authView').html())
    },
    events: {
        'click @ui.toReg' : 'toRegistration',
        'click @ui.auth' : 'authorization',
    }
    ,
    ui: {
        toReg: '#toRegButton',
        auth: '#authButton'
    }
    ,
    toRegistration(){
        console.log("Hey");
        work.navigate("registration", {trigger: true});
    },
    authorization(){
        $('#auth').submit(function (e) {
            e.preventDefault();

            $.ajax({
                url:   '/firstproject_war/rest/log/auth'  , //url страницы (action_ajax_form.php)
                type:     'POST', //метод отправки
                dataType: 'text', //формат данных
                data: $('#auth').serialize(),  // Сеарилизуем объект
                success: function(response) { //Данные отправлены успешно
                    console.log("Success");
                    console.log(response);
                    work.navigate("friends", {trigger: true});
                },
                error: function(response) { // Данные не отправлены
                    console.log("Error");
                    console.log(response);
                    alert('Something went wrong, try again');
                }
            })
        });
    }

});