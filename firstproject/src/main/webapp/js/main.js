const MyApp = Mn.Application.extend({
    initialize: function(options) {
    }
});

const myApp = new MyApp();

const Workspace = Backbone.Router.extend({

    routes: {
        "auth":                 "auth",
        "registration":         "registration",
        "friends":              "friends"
    },

    help: function() {
        console.log("help");
    },

    auth: function() {
        nextWindow(Auth);
    },
    registration: function() {
        nextWindow(Reg);
    },
    friends: function () {
        nextWindow(Profile);
    }
});
const work = new Workspace();

const MyRegion = Mn.Region.extend({ el: $('#myArea') });
const mainRegion = new MyRegion();
const MenuRegion = Mn.Region.extend({ el: $('#firstPart') });
const menuRegion = new MenuRegion();

const Friend = Backbone.Model.extend({
});

const Friends = Backbone.Collection.extend({
    model: Friend
});

myApp.on('start', function() {
    Backbone.history.start({pushState: true, root: "/firstproject_war/"});
    work.navigate("registration", {trigger: true});
    menuRegion.show(new Menu());
});

const Menu = Mn.View.extend({
    template: _.template(` It should be a menu`)
});

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
    <td>Login</td>
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

const FriendView = Mn.View.extend({
    tagName: "tr",
    initialize(){
        this.template = _.template($('#friendListJS').html())
    },
    render: function() {
        this.$el.html(this.template(this.model.attributes));
        return this;
    },
});

const Profile = Mn.View.extend({
    regions: {
        searchRegion: '#search',
    },
    template: _.template(`
    <div id="search"></div>
    <div>
        <tbody id="friendList"></tbody>
</div>
`),
    onRender(){
        this.showChildView('searchRegion', new SearchFriend());
    }
});

const SearchFriend = Mn.View.extend({
    template: _.template(` <form id="addFriend" method="post">
    <table>
    <tr>
    <td>Add to you friend list</td>
    <td colspan="2">
    <input type="text" name="login" placeholder="login">
    </td>
    </tr>
    <tr>
    <td>
    <button id="addFriendButton" value="submit">Add</button>
    </td>
    <td></td>
    <td>
    </td>
    </tr>
    </table>
    </form>`),
    events: {'click @ui.addFriend' : 'addingFriend'},
    ui: {addFriend : '#addFriendButton'},
    addingFriend(){
        $('#addFriend').submit(function (e) {
            e.preventDefault();
            const that = this;
            console.log("Start add request");
            $.ajax({
                url:   '/firstproject_war/rest/friend/add'  , //url страницы (action_ajax_form.php)
                type:     'POST', //метод отправки
                dataType: 'text', //формат данных
                data: $('#addFriend').serialize(),  // Сеарилизуем объект
                success: function(response) { //Данные отправлены успешно
                    console.log("Success");
                    console.log(response);
                    console.log("End add request");
                    nextWindow(Profile, response);
                    //nextWindow(NewProfile, response);
                },
                error: function(response) { // Данные не отправлены
                    console.log("Error");
                    console.log(response);
                    alert('Something went wrong, try again');
                }
            });
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
                            <input type="text" name="login" placeholder="login" id="login">
                        </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td colspan="2">
                            <input type="text" name="password" placeholder="password" id="password">
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <button id="toAuthButton">Authorization</button>
                        </td>
                        <td>Registration</td>
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
    },
    modelEvents:{
        'sync' : 'renderProfile',
        'error': 'errorReg'
    },
    toAuthorization(){
        console.dir("To auth");
        work.navigate("auth", {trigger: true});
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
                    work.navigate("friends", {trigger: true});
                },
                error: function(response) { // Данные не отправлены
                    console.log("Error");
                    alert('Something went wrong, try again');
                }
            })
        });
    }
});



function nextWindow(nextType, message) {
    mainRegion.show(new nextType());
    $('#myInfo').html(message);
}

myApp.start();
