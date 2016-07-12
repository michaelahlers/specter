package consulting.ahlers.specter

import de.flapdoodle.embed.process.config.store.{FileSet, FileType, IPackageResolver}
import de.flapdoodle.embed.process.distribution.ArchiveType._
import de.flapdoodle.embed.process.distribution.BitSize._
import de.flapdoodle.embed.process.distribution.Platform._
import de.flapdoodle.embed.process.distribution.{ArchiveType, Distribution, Platform}

import scala.language.postfixOps

/**
 * @author [[mailto:michael@ahlers.consulting Michael Ahlers]]
 */
object PhantomPackageResolver
  extends IPackageResolver {

  override def getArchiveType(distribution: Distribution): ArchiveType =
    distribution getPlatform match {
      case Linux => TBZ2
      case OS_X | Windows => ZIP
      case platform => throw UnsupportedPlatformException(platform)
    }

  override def getPath(distribution: Distribution): String = {

    val version = distribution.getVersion.asInDownloadPath

    val classifier: String =
      distribution getPlatform match {
        case Linux => "linux"
        case OS_X => "macosx"
        case Windows => "windows"
        case platform => throw UnsupportedPlatformException(platform)
      }

    val bitsize: Option[String] =
      (distribution getPlatform, distribution getBitsize) match {
        case (Linux, B32) => Some("i686")
        case (Linux, B64) => Some("x86_64")
        case _ => None
      }

    val extension: String =
      getArchiveType(distribution) match {
        case TBZ2 => "tar.bz2"
        case ZIP => "zip"
        case archive => throw UnsupportedArchiveException(archive)
      }

    s"phantomjs-$version-$classifier${bitsize.fold("")("-" +)}.$extension"
  }

  override def getFileSet(distribution: Distribution): FileSet = {
    val builder = FileSet.builder()

    distribution getPlatform match {
      case Linux | OS_X =>
        builder.addEntry(FileType.Executable, "phantomjs")
      case Windows =>
        builder.addEntry(FileType.Executable, "phantomjs.exe")
      case platform =>
        throw UnsupportedPlatformException(platform)
    }

    builder.build()
  }

  case class UnsupportedPlatformException(platform: Platform) extends UnsupportedOperationException(s"""Unknown platform "$platform".""")

  case class UnsupportedArchiveException(archive: ArchiveType) extends UnsupportedOperationException(s"""Unknown archive type "$archive".""")

}
