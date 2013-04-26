/**
 * Created with IntelliJ IDEA.
 * User: jcd
 * Date: 4/26/13
 * Time: 11:04 AM
 */
class Word (val lentgh:Int){
	var numOfN = 0
	var numOfYouon = 0
	var numOfFSyllables = 0
	var numOfVSyllables = 0
	private val value:StringBuilder = new StringBuilder()
	def append(syllable:Syllable){
		value.append(syllable.toString)
	}
	override def toString:String = {
		value.toString()
	}
	def n():Int = {
		numOfN / lentgh * 100
	}
	def youyon():Int =  {
		numOfYouon / lentgh * 100
	}
	def fsyl():Int = {
		numOfFSyllables / lentgh * 100
	}
	def vsyl():Int = {
		numOfVSyllables / lentgh * 100
	}
}
