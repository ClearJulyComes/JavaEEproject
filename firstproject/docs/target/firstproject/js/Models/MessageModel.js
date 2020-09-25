const Message = Backbone.Model.extend({
    defaults: {
        msg: '',
        userLogin: '',
        hisFriend: '',
        messageId: ''
    },
    validate:function (attr) {
        if(!attr.msg){
            return "Пустое сообщение";
        }
    }
});

const Messages = Backbone.Collection.extend({
    url: 'http://localhost:8080/firstproject_war/rest/messages/get',
    model: Message,
    sort_key: 'id',
    comparator: function (item) {
        return item.get(this.sort_key);
    },
    sortByField: function(fieldName) {
        this.sort_key = fieldName;
        this.sort();
    }
});

let messages = new Messages();