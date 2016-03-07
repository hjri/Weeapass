var _ = require('lodash'),
    baked = false;
    chance = new require('chance')(),
    classes = [
        'syllable',
        'vowel-only',
        'n',
        'youon',
        'syllable-youon'
    ],
    consonants = {
        'b': 100,
        'c': 75,
        'd': 100,
        'f': 50,
        'g': 100,
        'h': 50,
        'j': 100,
        'k': 100,
        'l': 'r',
        'm': 100,
        'n': 75,
        'p': 50,
        'q': 0,
        'r': 50,
        's': 100,
        't': 0,
        'v': 25,
        'w': 'v',
        'x': 0,
        'z': 50
    },
    vowels = ['a', 'i', 'e', 'o', 'u'],
    youonConsonants = ['k', 's', 'c'],
    generateFunctions = {
        'syllable': function(vowel, consonant) {
            return consonant + vowel;
        },
        'youon': function(vowel) {
            return 'y' + vowel;
        },
        'n': function() {
            return 'n';
        },
        'syllable-youon': function(vowel, consonant) {
            return consonant + 'y' + vowel;
        },
        'vowel-only': function(vowel) {
            return vowel;
        }
    },
    bakeArrays = function(options) {
        // Make reusable??
        if (baked) return;
        options = options || {};
        consonants = _.each(consonants, function(v, k) {
            if (typeof v === 'string') {
                consonants[k] = consonants[v];
            };
        });

        // make c2sh and c2t cross-compatible?
        if (options.s2sh) {
            consonants.sh = consonants.s;
            consonants.ch = consonants.c;
            delete consonants.s;
            delete consonants.c;
        }
        if (options.c2t) {
            consonants.t = consonants.c;
            delete consonants.c;
        }
        options.engrish && delete consonants.l;
        options.noW && delete consonants.w;
        options.noV && delete consonants.v;
        baked = true;
    },
    generateNextSyllable = function(previousSyllables) {
        var classProbability = {
            'syllable': 100,
            'syllable-youon': 12,
            'n': 25,
            'vowel-only': 50,
            'youon': 25
        };
        // TODO add '-' support?
        if (_.last(previousSyllables) && _.last(previousSyllables).class === 'n') {
            classProbability.n = 0;
        }
        classProbability['vowel-only'] = classProbability['vowel-only'] /
            (_.filter(previousSyllables, function(syll) {
                return syll.class === 'vowel';
            }).length + 1);
        var nextClass = chance.weighted( _.keys(classProbability), _.values(classProbability)),
            consSet = _.includes(nextClass, 'youon') ? _.pick(consonants, youonConsonants) : consonants,
            consonant = chance.weighted(_.keys(consSet),_.values(consSet)),
            vowel = chance.pick(vowels),
            next = generateFunctions[nextClass](vowel, consonant);

        return {
            class: nextClass,
            text: next
        };
    },
    word = [],
    makeWord = function(options, length) {
        bakeArrays(options);
        var index = 0,
            word = [];
        for(index;index<length;index++){
            word.push(generateNextSyllable(word));
        }
        return _(word).map(function(w){return w.text;}).join('');
    };
module.exports = makeWord;
