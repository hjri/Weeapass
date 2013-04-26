/**
 * Created with IntelliJ IDEA.
 * User: jcd
 * Date: 4/25/13
 * Time: 10:43 PM
 *
 * sample workspace file to view how generator behaves.
 */
val s:SyllableGenerator = new SyllableGenerator(false, false, true)
val w:WordGenerator = new WordGenerator(s)
for (i <- 1 to 50){
	System.out.println (w.generateWord.toString+" ")
}



































