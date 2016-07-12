package consulting.ahlers.specter

import de.flapdoodle.embed.process.distribution.{BitSize, Distribution}
import org.scalatest._

/**
 * @author [[mailto:michael@ahlers.consulting Michael Ahlers]]
 */
class PhantomDownloadsSpec
  extends FlatSpec
          with Matchers {

  val artifactStore = PhantomArtifactStoreBuilder.build()

  val distributions =
    for {
      version <- PhantomVersion.all
      platform <- PhantomPlatform.all
      bitsize <- BitSize.values
    } yield new Distribution(version, platform, bitsize)

  distributions foreach { distribution =>
    it must s"download PhantomJS for $distribution" in {
      artifactStore.checkDistribution(distribution) should be(true)
    }
  }

}
