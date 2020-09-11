const FriendsContainer = Mn.View.extend({
    initialize(){
        this.template = _.template($('#friendListContainer').html());
    },
    onRender(){
        console.log("render on Sync");
        const self = this;
        $(this.el).find("#friendListUL").empty();
        console.log(this.model.toArray().length + " array length");
        _.each(this.model.toArray(), function(friend){
            self.renderFriend(friend);
        }, this);
    },
    renderFriend(friend){
        const newFriend = new FriendView({
            model: friend
        });
        $(this.el).find("#friendListUL").append(newFriend.render().el);
    }
});

const FriendView = Mn.View.extend({
    tagName: "div",
    initialize(){
        this.template = _.template(`<span> <%= userLogin %> </span>
            <button>Write</button>
            <button class="deleteFriendButton">Delete</button>`)
    },
    ui: {deleteButton : '.deleteFriendButton'},
    events: {'click @ui.deleteButton' : 'deleteFriend'},
    render:function() {
        this.$el.html(this.template(this.model.toJSON()));
        return this;
    },
    deleteFriend(){
        const self = this;
        $.ajax({
            url:   '/firstproject_war/rest/friend/delete'  , //url страницы (action_ajax_form.php)
            type:     'POST', //метод отправки
            dataType: 'text', //формат данных
            data: this.model.toJSON(),  // Сеарилизуем объект
            success: function(response) { //Данные отправлены успешно
                console.log("Success delete request");
                self.remove();
            },
            error: function() { // Данные не отправлены
                console.log("Error delete request");
                alert('Something went wrong, try again');

            }
        });
    }
});