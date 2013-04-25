/**
 * Created with IntelliJ IDEA.
 * User: jcd
 * Date: 4/25/13
 * Time: 9:49 PM
 */
class Syllable (val consonant: String, val vowel: String){
	val value: String = consonant + vowel
	val consonantOnly : Boolean = vowel == "n"
	val vowelOnly : Boolean = consonant == ""
	override def toString:String = value
}