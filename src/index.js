#!/usr/bin/env node
/**
 * Module dependencies.
 */

var program = require('commander'),
    makeWord = require('./makeWord');

program
    .version('0.9.0')
    .option('-r, --engrish', 'Removes "l" from alphabet, as it should be.')
    .option('-w, --no-w', 'Removes "w" from alphabet.')
    .option('-v, --no-v', 'Removes "v" from alphabet.')
    .option('-n [number]', 'Sets amount of syllables to generate in a word, default = 6.', 6)
    .parse(process.argv);

console.log(makeWord({
    engrish: program.engrish,
    noW: !program.w,
    noV: !program.v
}, program.N));

