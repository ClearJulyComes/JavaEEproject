    const TextMessageView = Mn.View.extend({
        initialize() {
            this.template = _.template(`<input type="text" id="messageTextBox" name="login" placeholder="Type your message">
            <button id="sendMessageButton"> Send </button>`);
        },
        ui: {sendButton: '#sendMessageButton'},
        events: {'click @ui.sendButton': 'sendMessage'},
        sendMessage() {
            chatView.sendNewMessage();
        }
    });