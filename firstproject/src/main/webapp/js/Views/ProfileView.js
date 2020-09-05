const Profile = Mn.View.extend({
    el: $('#myArea'),
    template: _.template(`
        <div id="search"></div>
        <div id="friends_container">
            <ul id="friendList"></ul>
        </div>
    `),
    /*regions: {
        searchRegion: {
            el:$('#search')
        },
        friendsRegion: {
            el: $('#friends_container')
        }
    },*/
    render: function () {
        //this.showChildView('friendsRegion', new FriendsContainer());
        //this.showChildView('friendsRegion', new FriendsContainer());
    }
});

const myFriends = new Friends();

const FriendsContainer = Mn.View.extend({
    model: myFriends,
    initialize: function(){
        this.model.on('sync', this.render, this);
    },
    render: function(){
        const self = this;
        $('#friendList').empty();
        _.each(this.model.toArray(), function(friend){
            self.renderFriend(friend);
        }, this);
    },
    renderFriend: function(friend){
        const newFriend = new FriendView({
            model: friend
        });
        $('#friendList').append(newFriend.render().el)
    }
});

const FriendView = Mn.View.extend({
    tagName: "li",
    model: new Friend(),
    initialize: function(){
        this.template = _.template(`<span class="friendName"> <%= hisFriend %> </span>
            <button>Delete</button>`)
    },
    render: function() {
        this.$el.html(this.template(this.model.toJSON()));
        return this;
    },
});



const SearchFriend = Mn.View.extend({
    initialize(){
        this.template = _.template($('#searchFriendView').html())
    },
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
                    myFriends.fetch();
                    //nextWindow(Profile, response);
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