    const FriendsContainer = Mn.View.extend({
        model: friends,
        initialize() {
            this.template = _.template($('#friendListContainer').html());
        },
        onRender() {
            const self = this;
            $(this.el).find("#friendListUL").empty();
            _.each(this.model.toArray(), function (friend) {
                self.renderFriend(friend);
            }, this);
        },
        renderFriend(friend) {
            const newFriend = new FriendView({
                model: friend
            });
            $(this.el).find("#friendListUL").append(newFriend.render().el);
        },
        addFriend(data) {
            const friendModel = new Friend({
                userLogin: JSON.parse(data).hisFriend
            });
            this.model.add(friendModel);
            messages.fetch();
            this.onRender();
        },
        deleteFriend(data) {
            const self = this;
            _.each(this.model.toArray(), function (friend) {
                if (friend.toJSON().userLogin === JSON.parse(data).hisFriend) {
                    self.model.remove(friend);
                }
            });
            this.onRender();
        }
    });
    let friendsContainer = new FriendsContainer();

    const FriendView = Mn.View.extend({
        tagName: "tr",
        initialize() {
            this.template = _.template(`<td><span> <%= userLogin %> </span> </td>
            <td><button class="writeButton btn btn-dark">Write</button> </td>
            <td><button class="deleteFriendButton btn btn-dark">Delete</button></td>`)
        },
        ui: {
            deleteButton: '.deleteFriendButton',
            writeButton: '.writeButton'
        },
        events: {
            'click @ui.deleteButton': 'deleteFriend',
            'click @ui.writeButton': 'writeButton'
        },
        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },
        deleteFriend() {
            friendSocket.send(JSON.stringify(this.model.toJSON()));
        },
        writeButton() {
            const self = this;
            appRouter.navigate("message/" + self.model.toJSON().userLogin, {trigger: true});
        }
    });