const net = require("net");
const client = new net.Socket();

// default IP for our Rio, assuming it was setup according to the instructions
client.connect(10022, "10.10.02.2", () => {
	console.log("connected");
	console.log(new Date().getTime());
});

client.on("data", (m) => {
	let recieved = m.toString("utf-8")
	console.log(recieved);
	// to find the unix timestamp from the bot
	let data = recieved.split(" ");
	// local unix timestamp
	const timestamp = new Date().getTime();
	console.log(
		"Ping: " + 
		(timestamp - parseInt(data[data.length - 1])) / 1000 + // subtract the difference and divide to get ms
		" ms"
	);
});
