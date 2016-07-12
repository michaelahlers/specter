package consulting.ahlers.specter

import de.flapdoodle.embed.process.distribution.Platform._

/**
 * @author [[mailto:michael@ahlers.consulting Michael Ahlers]]
 */
object PhantomPlatform {

  val all =
    Linux ::
      OS_X ::
      Windows ::
      Nil

}
