    const TextMessageView = Mn.View.extend({
        initialize() {
            this.template = _.template(`<input type="text" id="messageTextBox" name="login" placeholder="Type your message">
            <button id="sendMessageButton"> Send </button>`);
        },
        ui: {
            sendButton: '#sendMessageButton',
            Box: '#messageTextBox'
        },
        events: {
            'click @ui.sendButton': 'sendMessage',
            'keydown @ui.Box' : 'searchKeywords'
        },
        searchKeywords(e){
            console.log(e.which + " pressed");
            if ( e.which === 13 ) {
                this.sendMessage();
            }
        },
        sendMessage() {
            chatView.sendNewMessage();
        }
    });