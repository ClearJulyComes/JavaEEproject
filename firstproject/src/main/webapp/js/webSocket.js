let socket = new WebSocket("ws://localhost:8080/firstproject_war/messages/" + userUrl);

socket.onopen = function(e) {
    console.log("Open socket connection")
    //socket.send("Меня зовут Джон");
};

socket.onmessage = function(event) {
    //alert(`[message] Данные получены с сервера: ${event.data}`);
    console.log("Get message from the serv");
    console.log(event.data.hisFriend + " data1");
    if(event.data.hisFriend === undefined){
        chatView.addMessage(event.data);
        
    }
};

socket.onclose = function(event) {
    console.log("Close the connection");
};

socket.onerror = function(error) {
    alert(`[error] ${error.message}`);
};