const ChatView = Mn.View.extend({
    friendLogin: '',
    model: new Messages,
    initialize(){
        this.template = _.template(`<div id="messagesBox"></div>
            <div id="typeMessage"></div>`);
    },
    regions:{
        typeText:{
            el: '#typeMessage'
        }
    },
    onRender(){
        console.log("render on Sync");
        const self = this;
        this.showChildView("typeText", new TextMessageView());
        $(this.el).find("#messagesBox").empty();
        console.log(this.model.toArray().length + " array length");
        _.each(this.model.toArray(), function(message){
            self.renderMessage(message);
        }, this);
    },
    renderMessage(message){
        const newMessage = new MessageView({
            model: message
        });
        $(this.el).find("#messagesBox").append(newMessage.render().el);
    },
    sendNewMessage: function(){
        const newMessage = new Message({
            msg: $("#messageTextBox").val(),
            userLogin: userUrl,
            hisFriend: this.friendLogin,
            messageId: ''
        });
        console.log("Sending message");
        socket.send(JSON.stringify(newMessage.toJSON()));
    },
    addMessage(data){
        console.log(data);
        var obj = JSON.parse(data);
        const newMessage = new Message({
            msg: $("#messageTextBox").val(),
            userLogin: obj.userLogin,
            hisFriend: this.friendLogin,
            messageId: obj.id
        });
        console.log(newMessage);
        this.model.add(newMessage);
        console.log("Add message");
        this.onRender();
    },
    setFriendLogin(userLogin){
        this.friendLogin = userLogin;
    },
    fetchMessages(){
        const self = this;
        this.model.fetch({
            data: $.param({ userTo: self.friendLogin}),
            success: function (response) {
                console.log(response + " success message fetch");
                self.onRender();
            },
            error: function () {
                console.log("error fetch")
            }
        });
    }
});
const chatView = new ChatView();

const MessageView = Mn.View.extend({
    tagName: "div",
    initialize(){
        this.template = _.template(`<span> <%= userLogin %> <br> <%= msg %> <%= messageId %></span>`)
    },
    render:function() {
        this.$el.html(this.template(this.model.toJSON()));
        return this;
    }
});