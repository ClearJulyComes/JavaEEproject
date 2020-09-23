    const ChatView = Mn.View.extend({
        friendLogin: '',
        model: messages,
        initialize() {
            this.template = _.template(`<div id="messagesBox"></div>
            <div id="typeMessage"></div>`);
            console.log("Init chat");
        },
        regions: {
            typeText: {
                el: '#typeMessage'
            }
        },
        onRender() {
            console.log("render Chat. friend login is " + this.friendLogin);
            const self = this;
            this.showChildView("typeText", new TextMessageView());
            $(this.el).find("#messagesBox").empty();
            console.log(this.model.toArray().length + " array length");
            _.each(this.model.toArray(), function (message) {
                console.log("each user login " + message.toJSON().userLogin + " and msg " + message.toJSON().msg);
                if((message.toJSON().userLogin === self.friendLogin)||(message.toJSON().hisFriend === self.friendLogin)) {
                    console.log("our user login " + message.toJSON().userLogin + " and msg " + message.toJSON().msg);
                    self.renderMessage(message);
                }
            }, this);
        },
        renderMessage(message) {
            let newMessage;
            if(message.toJSON().userLogin === userUrl) {
                newMessage = new MessageView({
                    model: message,
                    className: 'myMsg'
                });
            }else {
                newMessage = new MessageView({
                    model: message,
                    className: 'hisMsg'
                });
            }
            $(this.el).find("#messagesBox").append(newMessage.render().el);
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
            console.log("Sending message");
            socket.send(JSON.stringify(messageObj));
        },
        addMessage(data) {
            console.log(data);
            var obj = JSON.parse(data);
            const newMessage = new Message({
                msg: obj.msg,
                userLogin: obj.userLogin,
                hisFriend: obj.hisFriend,
                messageId: obj.messageId
            });
            console.log(newMessage);
            this.model.add(newMessage);
            console.log("Add message");
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
            this.template = _.template(`<span> <%= userLogin %> <br> <%= msg %> <%= messageId %></span>`)
        },
        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }
    });