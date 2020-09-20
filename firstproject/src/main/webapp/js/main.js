/*const MyApp = Mn.Application.extend({
    region: '#wrap'
});
const myApp = new MyApp();
myApp.on('start', function() {
    Backbone.history.start({pushState: true, root: "/firstproject_war/"});
});
const friend1 = new Friend({});
const wrapper = new Wrapper();
const router = new Router();
const profileView = new Profile();
const myFriends = new Friends(friend1);
const chatView = new ChatView({initialize(){
        this.model.on('sync', this.onRender(), this);}});
myApp.showView(wrapper);
myFriends.fetch();
const friendsContainer = new FriendsContainer({model: myFriends, initialize(){
    this.model.on('sync', this.onRender(), this);}});

$('body').on('click', 'a[href^="/"]', function (e) {
    e.preventDefault();
    router.navigate($(this).attr('href'), {trigger: true});
});
function destroyProfileView() {
    profileView.remove();
}

function renderWrapperNewView(type){
    wrapper.showChildView('mainRegion', new type());
}
function renderProfileView(){
    wrapper.showChildView('mainRegion', profileView);
}
function renderChatView(){
    wrapper.showChildView('mainRegion', chatView);
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
function fetchMessage(){
    chatView.model.fetch({data:JSON.stringify({ friend: id}),
        success: function (response) {
            console.log(response + " success messages fetch")
            chatView.onRender();
        },
        error: function () {
            console.log("error fetch")
        }
    });
}
function sendNewMessage(){
    const newMessage = new Message({
        message: $("#messageTextBox").val(),
        from: 'me',
        to: 'You',
        messageId: '33'
    });
    messages.add(newMessage);
    chatView.onRender();
}

function renderFriendsContainer() {
    profileView.showChildView('friendsRegion', friendsContainer);
}

function renderSearch(){
    profileView.showChildView('searchRegion', new SearchFriend());
}
myApp.start();
*/