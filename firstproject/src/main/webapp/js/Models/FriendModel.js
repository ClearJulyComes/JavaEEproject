const Friend = Backbone.Model.extend({
    defaults: {
        userLogin: ''
    },
    validate:function (attr) {
        if(!attr.userLogin){
            return "Field is empty"
        }
    }
});
const Friends = Backbone.Collection.extend({
    url: 'http://localhost:8080/firstproject_war/rest/friend/get',
    model: Friend
});
let friends = new Friends();
friends.on('error', function (model, error) {
    alert(error);
});