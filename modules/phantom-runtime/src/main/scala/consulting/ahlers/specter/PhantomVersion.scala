package consulting.ahlers.specter

import de.flapdoodle.embed.process.distribution.IVersion

/**
 * @author [[mailto:michael@ahlers.consulting Michael Ahlers]]
 */
sealed class PhantomVersion(val name: String)
  extends IVersion {

  override def asInDownloadPath(): String = name

}

object PhantomVersion {

  case object V211 extends PhantomVersion("2.1.1")

  val all =
    V211 ::
      Nil

}
