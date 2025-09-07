<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>WebSocket Demo</title>
</head>
<body>
<h2>WebSocket Chat</h2>
<div id="messages"></div>
<input type="text" id="msgInput" placeholder="Enter message..." />
<button onclick="sendMessage()">Send</button>

<script>
    // Kết nối WebSocket tới server
    const ws = new WebSocket("ws://localhost:8080/your-app-name/chat");

    ws.onopen = () => {
        console.log("Connected to WebSocket");
    };

    ws.onmessage = (event) => {
        const messagesDiv = document.getElementById("messages");
        messagesDiv.innerHTML += "<p>" + event.data + "</p>";
    };

    ws.onclose = () => {
        console.log("Disconnected from WebSocket");
    };

    ws.onerror = (error) => {
        console.error("WebSocket Error:", error);
    };

    function sendMessage() {
        const input = document.getElementById("msgInput");
        ws.send(input.value);
        input.value = "";
    }
</script>
</body>
</html>
