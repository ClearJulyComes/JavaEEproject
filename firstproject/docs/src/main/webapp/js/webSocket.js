let socket;

function messageWebSocket() {
    socket = new WebSocket("ws://clearjul.heliohost.org/minimal/messages/" + userUrl);
    socket.onopen = function (e) {
    };

    socket.onmessage = function (event) {
        if(JSON.parse(event.data).status === "send"){
            chatView.addMessage(event.data);
        }
    };

    socket.onclose = function (event) {
    };

    socket.onerror = function (error) {
        alert(`[error] ${error.message}`);
    };
}