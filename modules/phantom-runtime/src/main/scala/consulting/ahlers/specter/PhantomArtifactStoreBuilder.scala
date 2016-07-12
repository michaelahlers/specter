package consulting.ahlers.specter

import de.flapdoodle.embed.process.extract.UUIDTempNaming
import de.flapdoodle.embed.process.io.directories.PropertyOrPlatformTempDir
import de.flapdoodle.embed.process.store.{ArtifactStoreBuilder, Downloader}

/**
 * @author [[mailto:michael@ahlers.consulting Michael Ahlers]]
 */
object PhantomArtifactStoreBuilder
  extends ArtifactStoreBuilder {

  tempDir().setDefault(new PropertyOrPlatformTempDir())
  executableNaming().setDefault(new UUIDTempNaming())
  download().setDefault(PhantomDownloadConfigBuilder.build())
  downloader().setDefault(new Downloader())

}
