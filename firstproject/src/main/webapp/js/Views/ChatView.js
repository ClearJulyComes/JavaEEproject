    const ChatView = Mn.View.extend({
        friendLogin: '',
        model: messages,
        tagName: 'div',
        className: 'chatClass',
        initialize() {
            this.template = _.template($('#chatTmpl').html());
        },
        regions: {
            typeText: {
                el: '#typeMessage'
            }
        },
        onRender() {
            const self = this;
            this.showChildView("typeText", new TextMessageView());
            $(this.el).find("#messagesBox").empty();
            _.each(this.model.toArray(), function (message) {
                if((message.toJSON().userLogin === self.friendLogin)||(message.toJSON().hisFriend === self.friendLogin)) {
                    self.renderMessage(message);
                }
            }, this);
        },
        renderMessage(message) {
            let newMessage;
            if(message.toJSON().userLogin === userUrl) {
                newMessage = new MessageView({
                    model: message,
                    className: 'myMsg p-2 bg-secondary'
                });
            }else {
                newMessage = new MessageView({
                    model: message,
                    className: 'hisMsg p-2 bg-muted'
                });
            }
            $(this.el).find("#messagesBox").prepend(newMessage.render().el);
        },
        sendNewMessage: function () {
            const newMessage = new Message({
                msg: $("#messageTextBox").val().replace(/</g, "&lt;")
                    .replace(/>/g, "&gt;"),
                userLogin: userUrl,
                hisFriend: this.friendLogin,
                messageId: ''
            });
            let messageObj = newMessage.toJSON();
            messageObj.status = "send";
            socket.send(JSON.stringify(messageObj));
        },
        addMessage(data) {
            var obj = JSON.parse(data);
            const newMessage = new Message({
                msg: obj.msg,
                userLogin: obj.userLogin,
                hisFriend: obj.hisFriend,
                messageId: obj.messageId
            });
            this.model.add(newMessage);
            this.onRender();
        },
        setFriendLogin(userLogin) {
            this.friendLogin = userLogin;
        }
    });
    let chatView = new ChatView();

    const MessageView = Mn.View.extend({
        tagName: "div",
        initialize() {
            this.template = _.template(`<h5> <%= userLogin %> </h5> <br> <span> <%= msg %></span>`)
        },
        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }
    });