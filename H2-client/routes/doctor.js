var amqp = require('amqplib/callback_api');


var connection;
amqp.connect('amqp://admin:exchange@213.209.230.94:8088', function(err, conn) {
    connection = conn;
});

function doctorHome (res){
    res.render('./view/doctorHome');
}















/**  var ex = 'historyRequest';
    var args = process.argv.slice(2); 
    var key = (args.length > 0) ? args[0] : 'datacentre.request.history'; 
*/

var msg = ' {"type": "glycemia", "patientId": "giulia.lucchi", "start": "2018-01-11 11:28", "end": "2018-04-24 08:00:03", "requesterId": "mario.rossi", "requesterRole": "doctor"}';  

//var homeParameter = {    title: 'Web Server'}

/*function connectToTopic (req, res) {
        conn.createChannel(function(err, ch) {
            ch.assertExchange(ex, 'topic', {durable: false});
            ch.publish(ex, key, new Buffer(msg));
            console.log(" [x] Sent %s:'%s'", key, msg);
        });
    });
    res.render('index', homeParameter);
}*/


function _setProprietiesChannel(ex, args, key){
    var ex = ex;
    var args = args; 
    var key = key;   
}

module.exports = {doctorHome};