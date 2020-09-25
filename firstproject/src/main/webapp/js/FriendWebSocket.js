let friendSocket;
function friendWebSocket() {
    friendSocket = new WebSocket("ws://localhost:8080/firstproject_war/friends/" + userUrl);
    friendSocket.onopen = function(e) {
    };
    friendSocket.onmessage = function(event) {
        if(JSON.parse(event.data).status === "added"){
            friendsContainer.addFriend(event.data);
        }else if(JSON.parse(event.data).status === "deleted"){
            friendsContainer.deleteFriend(event.data)
        }else if(JSON.parse(event.data).status === "errorUserLogin"){
            alert(JSON.parse(event.data).msg);
        }else if(JSON.parse(event.data).status === "errorDB"){
            alert(JSON.parse(event.data).msg);
        }
    };
    friendSocket.onclose = function(event) {
        console.log("Close the connection");
    };
    friendSocket.onerror = function(error) {
        alert(`[error] ${error.message}`);
    };
}