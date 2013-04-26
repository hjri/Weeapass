package org.hjiri.weapass

/**
 * Created with IntelliJ IDEA.
 * User: jcd
 * Date: 4/25/13
 * Time: 9:54 PM
 */
class SyllableGenerator (val sInsteadOfsh: Boolean = false, val righto: Boolean = true){
	val sh = if (sInsteadOfsh) "s" else "sh"
	val r = if (righto) "r" else "l"
	val j = "j" // add option to replace with z?

	val commonConsonants   = "k" :: sh :: "t" :: "h"        :: "n" :: "m" :: r ::Nil
	val uncommonConsonants = "g" :: j  :: "d" :: "p" :: "b" ::Nil //gojūon
	val youonConsonants = "y"::Nil //or youon
	val epicConsonants = "w"::Nil
	val epicConsonantsAlt = "v"::Nil

	//if (useX) consonants = consonants :+ "x"

	val forW = "a"::"o"::Nil // wa\wo
	val forWRare = "i"::"e"::Nil //wi\we
	val forAll = "u"::Nil

	val forCommon = forW:::forWRare:::forAll
	val forYouon = forW:::forAll

	/**
	 * Generates full syllable, consonant+vowel
	 * @param youonForce force creating ya\yo\yu syllable
	 * @param youonProhibit don't create ?ya\?yo\?yu syllables, for word creation.
	 * @return resulting syllable
	 */
	def generateSyllableF(youonForce:Boolean, youonProhibit:Boolean):Syllable = {
		val rarity =
		if (youonForce){
			Rarity.Youon
		} else if (Randomizer.pickWithProb(0.80)){
			Rarity.Common
		} else if (Randomizer.pickWithProb(0.70)){
			Rarity.Uncommon
		} else if (Randomizer.pickWithProb(0.60)){
			Rarity.Youon
		} else if (Randomizer.pickWithProb(0.75)){
			Rarity.Epic
		} else {
			Rarity.EpicAlt
		}

		val consonants = rarity match {
			case Rarity.Common => commonConsonants
			case Rarity.Uncommon => uncommonConsonants
			case Rarity.Epic => epicConsonants
			case Rarity.Youon => youonConsonants
			case Rarity.EpicAlt => epicConsonantsAlt
		}
		val consonant = Randomizer.pickFrom(consonants)
		val probOfYouon = if (consonant == j) 0.05 else 0.15
		var generatedYouon = false
		val vowel =
		if (!youonForce && !youonProhibit && rarity != Rarity.Youon && Randomizer.pickWithProb(probOfYouon)){
			generatedYouon = true
			generateSyllableF(youonForce = true, youonProhibit = false).toString
		} else {
			val vowels = rarity match {
				case Rarity.Uncommon | Rarity.Common => forCommon
				case Rarity.Epic => if (Randomizer.pickWithProb(0.65)) forW else forWRare
				case Rarity.Youon => forYouon
				case Rarity.EpicAlt => forAll
			}
			Randomizer.pickFrom(vowels)
		}
		new Syllable(consonant,vowel,generatedYouon)
	}
	def generateSyllableF:Syllable = generateSyllableF(youonForce = false, youonProhibit = false)

	def generateSyllableV(useN:Boolean):Syllable = {
		val letters = if (useN) "n"::Nil else forCommon
		new Syllable("",Randomizer.pickFrom(letters),false)
	}

}
