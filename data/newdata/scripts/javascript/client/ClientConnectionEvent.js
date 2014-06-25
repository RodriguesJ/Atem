var net = require('net');
var PORT = 43594;
var HOST = '127.0.0.1';
var client = new net.Socket();

client.connect(PORT, HOST, function() {
	console.log('Connected: ' + PORT);
	client.write('My name is Josh!');
});
sock.on('data', function(data) {
	console.log('Data: ' + data);
	client.destroy();
});
client.on('close', function() {} {
	console.log('Disconnected.');
});