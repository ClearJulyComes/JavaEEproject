const Auth = Mn.View.extend({
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
        router.navigate("", {trigger: true});
    },
    authorization(){
        $('#auth').submit(function (e) {
            e.preventDefault();

            $.ajax({
                url:   '/firstproject_war/rest/log/auth'  , //url страницы (action_ajax_form.php)
                type:     'POST', //метод отправки
                dataType: 'text', //формат данных
                data: $('#auth').serialize(),  // Сеарилизуем объект
                success: function(response, xhr) { //Данные отправлены успешно
                    console.log("Success AJAX");
                    console.log(xhr.status);
                    if($.trim(response) === "fine"){
                        router.navigate("friends", {trigger: true});
                    }else {
                        alert('Wrong password');
                        renderWrapperNewView(Auth);
                    }
                },
                error: function() { // Данные не отправлены
                    console.log("Error AJAX");
                    alert('Wrong user login');
                    renderWrapperNewView(Auth);
                }
            })
        });
    }

});