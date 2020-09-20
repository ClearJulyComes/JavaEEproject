const SearchFriend = Mn.View.extend({
    initialize(){
        this.template = _.template($('#searchFriendView').html())
    },
    events: {'click @ui.addFriend' : 'addingFriend'},
    ui: {addFriend : '#addFriendButton'},
    addingFriend(){
        $('#addFriend').submit(function (e) {
            e.preventDefault();
            friendSocket.send($("#addFriend").serialize());
            profile.showChildView('searchRegion', new SearchFriend());
        });
    }
});