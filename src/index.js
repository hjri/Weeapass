/**
 * Module dependencies.
 */

var program = require('commander');

program
    .version('1.0.0')
    .option('-c, --cheese [type]', 'Add the specified type of cheese [marble]', 'marble')

console.log('you ordered a pizza with:');
if (program.peppers) console.log('  - peppers');
if (program.pineapple) console.log('  - pineapple');
if (program.bbqSauce) console.log('  - bbq');
console.log('  - %s cheese', program.cheese);
