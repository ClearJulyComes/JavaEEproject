const friend1 = new Friend({
    friendshipId: '1',
    hisFriend: 'Ann',
    userLogin: 'me'
});
const myFriends = new Friends(friend1);

const Profile = Mn.View.extend({
    template: _.template(`
        <div id="search"></div>
        <div id="friends_container"></div>
    `),
    regions: {
        searchRegion: {
            el:'#search'
        },
        friendsRegion: {
            el: '#friends_container'
        }
    },
    onRender() {
        this.showChildView('friendsRegion', new FriendsContainer({model: myFriends}));
        this.renderSearch();
    },
    renderSearch(){
        this.showChildView('searchRegion', new SearchFriend());
    }
});

