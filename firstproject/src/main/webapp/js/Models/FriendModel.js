const Friend = Backbone.Model.extend({
        defaults: {
                userLogin: '',
                userPassword: ''
        }
});

const Friends = Backbone.Collection.extend({
    url: 'http://localhost:8080/firstproject_war/rest/friend/get',
    model: Friend
});