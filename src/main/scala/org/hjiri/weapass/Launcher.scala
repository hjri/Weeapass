package org.hjiri.weapass
import com.beust.jcommander.{JCommander, Parameter}


/**
 * Created with IntelliJ IDEA.
 * User: jcd
 * Date: 4/25/13
 * Time: 9:46 PM
 */
object Launcher extends App{
  object Args {
    // Declared as var because JCommander assigns a new collection declared
    // as java.util.List because that's what JCommander will replace it with.
    // It'd be nice if JCommander would just use the provided List so this
    // could be a val and a Scala LinkedList.
    @Parameter(
      names = Array("-l"),
      description = "When genelatingu wolds use L instead of R")
    var lefto: Boolean = false

    @Parameter(
      names = Array("-s"),
      description = "When generating wordsh ushe S instead of SH")
    var shisi: Boolean = false
  }

  if (args != null){
    new JCommander(Args, args.toArray : _*)
  }
  val s:SyllableGenerator = new SyllableGenerator(!Args.shisi, !Args.lefto)
	val w:WordGenerator = new WordGenerator(s)
	for (i <- 1 to Randomizer.pickInt(2,5)){
		System.out.print (w.generateWord.toString+" ")
	}


}
