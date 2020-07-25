

const MyApp = Mn.Application.extend({
    initialize: function(options) {
    }
});

const myApp = new MyApp();

myApp.on('start', function() {
    nextWindow(Reg);
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
        nextWindow(Reg);
    },
    authorization(){
        $('#auth').submit(function (e) {
            e.preventDefault();

            $.ajax({
                url:   '/firstproject_war/rest/log/auth'  , //url страницы (action_ajax_form.php)
                type:     'POST', //метод отправки
                dataType: 'application/json', //формат данных
                data: $('#auth').serialize(),  // Сеарилизуем объект
                success: function(response) { //Данные отправлены успешно
                    console.log("Success");
                    alert(response);
                    //nextWindow(Profile);
                },
                error: function(response) { // Данные не отправлены
                    console.log("Error");
                    alert('Something went wrong, try again');
                }
            })
        });
    }

});

const Profile = Mn.View.extend({
    template: _.template(`
    <div>
        Everything ok!
    </div>
`)
});

const NewProfile = Mn.View.extend({
    template: _.template(`
    <div>
        Create your profile!
    </div>
`)
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
        nextWindow(Auth);
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
                    nextWindow(NewProfile);
                },
                error: function(response) { // Данные не отправлены
                    console.log("Error");
                    alert('Something went wrong, try again');
                }
            })
        });
    }
});

myApp.start();

function nextWindow(nextType) {
    mainRegion.show(new nextType());
}