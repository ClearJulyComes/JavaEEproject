var userUrl;

const Menu = Mn.View.extend({
    template: _.template(`<div><div id="profileMenu">Profile</div><div id="friendsMenu">Friends</div>
        <div id="exitMenu"><button id="exitButton">Exit</button> </div></div>`),
    events: {'click @ui.exitButton' : 'exitMethod'},
    ui: {exitButton : '#exitButton'},
    exitMethod(){
        console.log("Exit button pushed");
        $.ajax({
            url:   '/firstproject_war/rest/log/logout'  , //url страницы (action_ajax_form.php)
            type:     'POST', //метод отправки
            dataType: 'text', //формат данных
            data: "Check",  // Сеарилизуем объект
            success: function(response) { //Данные отправлены успешно
                console.log("Success AJAX exit");
                userUrl = undefined;
                appRouter.navigate("auth", {trigger: true});
            },
            error: function() {
                console.log("Error AJAX exit");
            }
        });
    }
});