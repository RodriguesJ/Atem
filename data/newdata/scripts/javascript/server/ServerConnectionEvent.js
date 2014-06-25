var net = require('net');
var PORT = 43594;
var HOST = '127.0.0.1';

net.createServer(function(sock) {
	console.log('Connected: ' + sock.remotePort);
	sock.on('data', function(data) {
		console.log('Data: ' + data);
		sock.write('You said "' + data + '"');
	});
	sock.on('close', function(data) {
		console.log('Disconnected: ' + sock.remotePort);
	});
}).listen(PORT, HOST);

console.log('Server connected to ' + HOST + ':' + PORT);