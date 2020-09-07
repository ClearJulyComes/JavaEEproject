const FriendsContainer = Mn.View.extend({

    initialize(){
        this.template = _.template($('#friendListContainer').html());
        this.model.on('sync', this.onRender(), this);
        this.model.fetch({
            success: function (response) {
                console.log(response + " success fetch")
            },
            error: function () {
                console.log("error fetch")
            }
        });
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
        this.template = _.template(`<span> <%= hisFriend %> </span>
            <button>Delete</button>`)
    },
    render:function() {
        console.log("render friendView");
        this.$el.html(this.template(this.model.toJSON()));
        return this;
    },
});
