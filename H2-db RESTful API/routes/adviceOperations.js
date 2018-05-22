/** RESTful API H2db
 * @author Giulia Lucchi
 * @author Margherita Pecorelli
 */

/** Model Schema collection */
var advice = require('../models/advice');

/** Adds an advice related to a particular patient
 * 
 * @throws 200 - OK
 *         500 - Internal Server Error
 * 
 * @param {String} message - advice to insert in JSON format
 * @param {Response} res - response of RESTful request
 */
function addAdvice(message, res){
    var mess  = JSON.parse(message);
    advice.create(mess, function(err, value){
        if(err){
            res.send(500);
        }else {
            res.send(200);
        }
    });
}

/** Returns all advices of a specific patient
 * 
 * @throws 500 - Internal Server Error
 *         
 * @returns a JSON with a list of all advices related to the patient
 * 
 * @param {String} idCode - patient identifier
 * @param {Response} res - response of RESTful request
 */
function getAdvices(idCode, res){
    advice.find({'patientId': idCode}, {'_id':0, 'patientId':0}, function(err, value){
        if(err){
            res.send(500);
        } else {
            res.json(value);
        }
    });
}

module.exports = {addAdvice, getAdvices}