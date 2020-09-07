const SearchFriend = Mn.View.extend({
    initialize(){
        this.template = _.template($('#searchFriendView').html())
    },
    events: {'click @ui.addFriend' : 'addingFriend'},
    ui: {addFriend : '#addFriendButton'},
    addingFriend(){
        $('#addFriend').submit(function (e) {
            e.preventDefault();

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
                    //myFriends.fetch();
                    renderNewView(Profile);
                    //Profile.renderSearch();
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