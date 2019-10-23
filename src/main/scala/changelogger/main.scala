package changelogger

import java.sql.Date

import sys.process._
import scala.io.Source

object Main {

  def main(args: Array[String]): Unit = {
    val DELIMITER = "----EOC-DELIMITER----";
    val GIT_LOG_COMMAND = "git log --format=%B%H"+DELIMITER
    val GET_REPO_VERSION_COMMAND = "gradle properties -q | grep \"version:\" | awk '{print $2}'"
    val README_FILE_PATH = "readme.md"
    val predefinedTypes = Array("feature, fix, revert, deprecation")

    val gitRepoLog = GIT_LOG_COMMAND!!
    val commitsArr = gitRepoLog.toString.split(DELIMITER+'\n').map(curr => curr.split('\n').head)

//    val oldReadmeLinesList = Source.fromFile(README_FILE_PATH).getLines.toList
//    val oldVersion = GET_REPO_VERSION_COMMAND!

    val featuresArray = commitsArr.filter(_.toLowerCase().startsWith("feature"))
    val typesMap = predefinedTypes.map(currType => Map(currType ->
        commitsArr.groupBy(_.toLowerCase().startsWith(currType))))

    for ((commitType, commits) <- typesMap) printf("type: %s, commits list: %s\n",commitType, commits)

//
//    const currentVersion = Number(require("./package.json").version);
//    const newVersion = currentVersion + 1;
//    let newChangelog = `# Version ${newVersion} (${
//      new Date().toISOString().split("T")[0]
//    })\n\n`;
//
//    sourceGenerators in Compile <+= (sourceManaged in Compile, version, name) map { (d, v, n) =>
//      val file = d / "info.scala"
//      IO.write(file, """package foo
//                       |object Info {
//                       |  val version = "%s"
//                       |  val name = "%s"
//                       |}
//                       |""".stripMargin.format(v, n))
//      Seq(file)
//    }

  }
}

//sudo apt-get remove scala-library scala
//sudo wget www.scala-lang.org/files/archive/scala-2.12.7.deb
//sudo dpkg -i scala-2.12.7deb