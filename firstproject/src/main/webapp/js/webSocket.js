let socket;

function messageWebSocket() {
    socket = new WebSocket("ws://localhost:8080/firstproject_war/messages/" + userUrl);
    socket.onopen = function (e) {
        console.log("Open socket connection")
    };

    socket.onmessage = function (event) {
        console.log("Get message from the serv");
        console.log(event.data + " data1");
        if(JSON.parse(event.data).status === "send"){
            chatView.addMessage(event.data);
        }
    };

    socket.onclose = function (event) {
        console.log("Close the connection");
    };

    socket.onerror = function (error) {
        alert(`[error] ${error.message}`);
    };
}