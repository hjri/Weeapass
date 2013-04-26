import scala.util.Random

/**
 * Created with IntelliJ IDEA.
 * User: jcd
 * Date: 4/25/13
 * Time: 11:54 PM
 */
object Randomizer {

	val secuRnd: Random = Random.javaRandomToRandom(new java.security.SecureRandom())

	def pickWithProb(prob:Double):Boolean = {
		secuRnd.nextDouble() <= prob
	}
	def pickFrom[T](prob:List[T]): T = {
	    prob(secuRnd.nextInt(prob.size))
	}
	def pickInt(min:Int,max:Int):Int = {
		min + secuRnd.nextInt(max - min)
	}
}
