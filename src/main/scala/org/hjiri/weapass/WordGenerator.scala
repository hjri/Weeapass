package org.hjiri.weapass

/**
 * Created with IntelliJ IDEA.
 * User: jcd
 * Date: 4/26/13
 * Time: 11:03 AM
 */
class WordGenerator(val sg:SyllableGenerator) {
	def generateWord:Word= {
		val word = new Word(Randomizer.pickInt(3,6))
		var lastYouon = false
		for (i <- 0 to word.lentgh){
			val syllable =
			if ((0 to 5 contains(word.n())) && Randomizer.pickWithProb(0.25)){
				sg.generateSyllableV(useN = true)
			} else if ((0 to 15 contains(word.vsyl())) && Randomizer.pickWithProb(0.25)){
				sg.generateSyllableV(useN = false)
			} else {
				sg.generateSyllableF(youonForce = false, youonProhibit = lastYouon)
			}
			lastYouon = syllable.isYouon
			word.append(syllable)
		}
		word
	}
}
