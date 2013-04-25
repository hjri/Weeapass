/**
 * Created with IntelliJ IDEA.
 * User: jcd
 * Date: 4/25/13
 * Time: 9:54 PM
 */
class SyllableGenerator (val useX: Boolean, val sInsteadOfsh: Boolean = false, val righto: Boolean = true){
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


	def generateSyllableF(youon:Boolean):Syllable = {
		var rarity:Rarity.Value = null
		val consonants =
		if (youon){
			rarity = Rarity.Youon
			youonConsonants
		} else if (Randomizer.pickWithProb(0.50)){
			rarity = Rarity.Common
			commonConsonants
		} else if (Randomizer.pickWithProb(0.50)){
			rarity = Rarity.Uncommon
			uncommonConsonants
		} else if (Randomizer.pickWithProb(0.50)){
			rarity = Rarity.Youon
			youonConsonants
		} else if (Randomizer.pickWithProb(0.50)) {
			rarity = Rarity.Epic
			epicConsonants
		} else {
			rarity = Rarity.EpicAlt
			epicConsonantsAlt
		}
		val consonant = Randomizer.pickFrom(consonants)
		val probOfYouon = if (consonant == j) 0.05 else 0.15
		val vowel =
		if (!youon && rarity != Rarity.Youon && Randomizer.pickWithProb(probOfYouon)){
			generateSyllableF(youon = true).toString
		} else {
			val vowels = rarity match {
				case Rarity.Uncommon | Rarity.Common => forCommon
				case Rarity.Epic => if (Randomizer.pickWithProb(0.65)) forW else forWRare
				case Rarity.Youon => forYouon
				case Rarity.EpicAlt => forAll
			}
			Randomizer.pickFrom(vowels)
		}
		new Syllable(consonant,vowel)
	}
	def generateSyllableF:Syllable = generateSyllableF(youon = false)

	def generateSyllableV(includeN:Boolean):Syllable = {
		val letters = if (includeN) forAll:+"n" else forAll
		new Syllable("",Randomizer.pickFrom(letters))
	}

}
