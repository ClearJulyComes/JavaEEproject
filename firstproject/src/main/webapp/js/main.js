

const MyApp = Mn.Application.extend({
    initialize: function(options) {
    }
});

const myApp = new MyApp();

myApp.on('start', function() {
    mainRegion.show(new Reg());
});

const MyRegion = Mn.Region.extend({ el: $('#myArea') });
const mainRegion = new MyRegion();

const Auth = Mn.View.extend({
    template: _.template(`
        <div> 
            <form id="auth" method="post">
    <table>
    <tr>
    <td>Login</td>
    <td colspan="2">
    <input type="text" name="login" placeholder="login">
    </td>
    </tr>
    <tr>
    <td>Password</td>
    <td colspan="2">
    <input type="text" name="password" placeholder="password">
    </td>
    </tr>
    <tr>
    <td>
    <button id="toRegButton">Registration</button>
    </td>
    <td></td>
    <td>
    <button id="authButton">Ok</button>
    </td>
    </tr>
    </table>
    </form>
        </div>`
    ),
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
        mainRegion.show(new Reg());
    },
    authorization(){
        $('#auth').submit(function (e) {
            e.preventDefault();

            $.ajax({
                url:   "/auth"  , //url страницы (action_ajax_form.php)
                type:     "POST", //метод отправки
                dataType: "json", //формат данных
                data: $('#auth').serialize(),  // Сеарилизуем объект
                success: function(response) { //Данные отправлены успешно
                    console.log("Success");
                    //    result = $.parseJSON(response);
                    //    $('#result_form').html('Имя: '+result.name+'<br>Телефон: '+result.phonenumber);
                },
                error: function(response) { // Данные не отправлены
                    console.log("error");
                    //$('#result_form').html('Ошибка. Данные не отправлены.');
                }
            })
        });
    }

});

const Reg = Mn.View.extend({
    template: _.template(`
        <div>
            <form id="reg" method="post">
                <table>
                    <tr>
                        <td>Login</td>
                        <td colspan="2">
                            <input type="text" name="login" placeholder="login">
                        </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td colspan="2">
                            <input type="text" name="password" placeholder="password">
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <button id="toAuthButton">Authorization</button>
                        </td>
                        <td></td>
                        <td>
                            <button id="regButton">Ok</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>`
    )  ,
    events: {
        'click @ui.toAuth' : 'toAuthorization',
        'click @ui.reg' : 'registration',
    }
    ,
    ui: {
        toAuth: '#toAuthButton',
        reg: '#regButton'
    }
    ,
    toAuthorization(){
        console.dir("fuck you bitch!!!!");
        mainRegion.show(new Auth());
    },

});

myApp.start();