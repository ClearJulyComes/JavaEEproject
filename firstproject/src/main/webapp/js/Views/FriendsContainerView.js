const FriendsContainer = Mn.View.extend({
    model: new Friends,
    initialize(){
        this.template = _.template($('#friendListContainer').html());
        this.model.on('sync', this.onRender(), this);
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
    },
    fetchFriendsContainer(){
        const self = this;
        this.model.fetch({
            success: function (response) {
                console.log(response + " success fetch")
                self.onRender();
            },
            error: function () {
                console.log("error fetch")
            }
        });
    },
    addFriend(data){
        const self = this;
        const friendModel = new Friend({
            userLogin: JSON.parse(data).hisFriend
        });
        this.model.add(friendModel);
        this.onRender();
    },
    deleteFriend(data){
        const self = this;
        _.each(this.model.toArray(), function(friend){
            console.log("Friend name: " + friend.toJSON().userLogin);
            console.log("Friend JSON name: " + JSON.parse(data).hisFriend);
            if (friend.toJSON().userLogin === JSON.parse(data).hisFriend) {
                console.log("Removed");
                self.model.remove(friend);
            }
        });
        this.onRender();
    }
});
const friendsContainer = new FriendsContainer();

const FriendView = Mn.View.extend({
    tagName: "div",
    initialize(){
        this.template = _.template(`<span> <%= userLogin %> </span>
            <button class="writeButton">Write</button>
            <button class="deleteFriendButton">Delete</button>`)
    },
    ui: {
        deleteButton : '.deleteFriendButton',
        writeButton : '.writeButton'
    },
    events: {
        'click @ui.deleteButton' : 'deleteFriend',
        'click @ui.writeButton' : 'writeButton'
    },
    render:function() {
        this.$el.html(this.template(this.model.toJSON()));
        return this;
    },
    deleteFriend(){
        const self = this;
        friendSocket.send(JSON.stringify(this.model.toJSON()));
    },
    writeButton(){
        const self = this;
        appRouter.navigate("message/"+self.model.toJSON().userLogin, {trigger: true});
    }
});