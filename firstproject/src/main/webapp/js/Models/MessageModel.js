const Message = Backbone.Model.extend({
    defaults: {
        msg: '',
        userLogin: '',
        hisFriend: '',
        messageId: ''
    }
});

const Messages = Backbone.Collection.extend({
    url: 'http://localhost:8080/firstproject_war/rest/messages/get',
    model: Message
});

let messages = new Messages();