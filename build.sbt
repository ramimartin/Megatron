organization := "ar.rami"

name := "Megatron"

version := "1.0"

scalaVersion := "2.11.4"

//unmanagedBase := baseDirectory.value / "lib"

//unmanagedJars in Compile := (baseDirectory.value ** "*.jar").classpath

val botDirectory = SettingKey[File]("bot-directory")

val scalatronFile = SettingKey[File]("scalatron-file")

val play = TaskKey[Unit]("play")

botDirectory := file("bots")

scalatronFile := file("lib_unmanaged/Scalatron.jar")

play <<= (botDirectory, name, javaOptions, scalatronFile in Compile, Keys.`package` in Compile) map { (bots, name, javaOptions, ucp, botJar) =>
  IO createDirectory (bots / name)
  IO copyFile (botJar, bots / name / "ScalatronBot.jar")

  val cmd = "java %s -cp %s scalatron.main.Main -plugins %s" format (
    javaOptions mkString " ",
    Seq(ucp, botJar).absString,
    bots.absolutePath)
  cmd run
}
