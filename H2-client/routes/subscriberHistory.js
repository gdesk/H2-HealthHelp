var amqp = require('amqplib/callback_api');
var session = require('client-sessions');

var connection;
var ex;
var args;
var key;
var queue;

amqp.connect('amqp://admin:exchange@213.209.230.94:8088', function(err, conn) {
    connection = conn;
});


function getDataHistory (res, idCode){
    ex = 'historyRequest';
    args = process.argv.slice(2);
    queue = "history";
    key = (args.length > 0) ? args[0] : ""+session.role+"."+idCode+".receive.history";
    console.log(key);
    connection.createChannel(function(err, ch) {
        ch.assertExchange(ex, 'topic', {durable: false});
    
        ch.assertQueue('history', {exclusive: false}, function(err, q) {
          console.log(" [*] Waiting for messages in %s. To exit press CTRL+C", q.queue);
          ch.bindQueue(q.queue, ex, key);
    
          ch.consume(q.queue, function(msg) {
                    console.log(" [x] %s", msg.content.toString());
                    res.render('historyPage', {title: 'Data History', patient: 'patient: '+session.pat, type: "sensor type: "+session.type, values: ""+msg.content.toString() });
          }, {noAck: true});
        });
      });
    
    
}


module.exports = {getDataHistory};
