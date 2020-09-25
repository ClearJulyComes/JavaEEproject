    const TextMessageView = Mn.View.extend({
        tagName: 'div',
        className: 'd-flex',
        initialize() {
            this.template = _.template(`<textarea class="p-2 form-control" type="text" id="messageTextBox" name="login"
                placeholder="Type your message"></textarea>
                <button class="p-2 btn btn-dark" id="sendMessageButton"> Send </button>`);
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
            if ( e.which === 13 ) {
                this.sendMessage();
            }
        },
        sendMessage() {
            chatView.sendNewMessage();
        }
    });