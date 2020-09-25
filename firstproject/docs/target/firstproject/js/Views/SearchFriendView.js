    const SearchFriend = Mn.View.extend({
        tagName: 'div',
        className: 'd-inline-flex p-3',
        initialize() {
            this.template = _.template($('#searchFriendView').html())
        },
        events: {'click @ui.addFriend': 'addingFriend'},
        ui: {addFriend: '#addFriendButton'},
        addingFriend() {
            $('#addFriend').submit(function (e) {
                e.preventDefault();
                friendSocket.send($("#addFriend").serialize().replace(/</g, "&lt;")
                    .replace(/>/g, "&gt;"));
                profile.showChildView('searchRegion', new SearchFriend());
            });
        }
    });