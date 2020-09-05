const Friend = Backbone.Model.extend({
        defaults: {
                friendshipId:'',
                hisFriend: '',
                userLogin: ''
        }
});

const Friends = Backbone.Collection.extend({
    urlRoot: 'http://localhost:8080/firstproject_war/rest/friend/get',
    model: Friend,
    comparator: function(friend){
        return friend.get('friendLogin');
    }
});