const MyApp = Mn.Application.extend({
    region: '#wrap'
});
const myApp = new MyApp();
myApp.on('start', function() {
    Backbone.history.start({pushState: true, root: "/firstproject_war/"});
});
const friend1 = new Friend({
    userLogin: 'Ann',
    userPassword: 'oo'
});
const wrapper = new Wrapper();
const router = new Router();
const profileView = new Profile();
const myFriends = new Friends(friend1);
myApp.showView(wrapper);
myFriends.fetch();
const friendsContainer = new FriendsContainer({model: myFriends, initialize(){
    this.model.on('sync', this.onRender(), this);
    this.model.on('change', this.onRender(), this)}});

$('body').on('click', 'a[href^="/"]', function (e) {
    e.preventDefault();
    router.navigate($(this).attr('href'), {trigger: true});
});

function renderWrapperNewView(type){
    wrapper.showChildView('mainRegion', new type());
}
function renderProfileView(){
    wrapper.showChildView('mainRegion', profileView);
}

function fetchFriendsContainer(){
    friendsContainer.model.fetch({
        success: function (response) {
            console.log(response + " success fetch")
            friendsContainer.onRender();
        },
        error: function () {
            console.log("error fetch")
        }
    });
}
function removeFriend(){
    friendsContainer.model.remove();
}

function renderFriendsContainer() {
    profileView.showChildView('friendsRegion', friendsContainer);
}

function renderSearch(){
    profileView.showChildView('searchRegion', new SearchFriend());
}
myApp.start();