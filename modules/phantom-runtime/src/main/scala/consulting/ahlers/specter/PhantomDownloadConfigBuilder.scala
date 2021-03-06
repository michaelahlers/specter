package consulting.ahlers.specter

import de.flapdoodle.embed.process.config.store.DownloadConfigBuilder.{DownloadPrefix, UserAgent}
import de.flapdoodle.embed.process.config.store.{DownloadConfigBuilder, DownloadPath}
import de.flapdoodle.embed.process.extract.UUIDTempNaming
import de.flapdoodle.embed.process.io.directories.UserHome
import de.flapdoodle.embed.process.io.progress.StandardConsoleProgressListener

/**
 * @author [[mailto:michael@ahlers.consulting Michael Ahlers]]
 */
object PhantomDownloadConfigBuilder
  extends DownloadConfigBuilder {

  fileNaming().setDefault(new UUIDTempNaming())

  /* Official download site appears problematic for programmatic user agents. See michaelahlers/specter#1 (and ariya/phantomjs#13951). */

  // downloadPath().setDefault(new DownloadPath("https://bitbucket.org/ariya/phantomjs/downloads/"))
  // downloadPath().setDefault(new DownloadPath("https://cnpmjs.org/downloads/"))
  downloadPath().setDefault(new DownloadPath("https://github.com/paladox/phantomjs/releases/download/2.1.7/"))

  progressListener().setDefault(new StandardConsoleProgressListener())
  packageResolver().setDefault(PhantomPackageResolver)
  artifactStorePath().setDefault(new UserHome(".embedded-phantom"))
  downloadPrefix().setDefault(new DownloadPrefix("phantom-download"))
  userAgent().setDefault(new UserAgent("Mozilla/5.0 (compatible Embedded Phantom +https://github.com/michaelahlers/specter)"))

}
