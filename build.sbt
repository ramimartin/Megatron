organization := "ar.rami"

name := "Megatron"

version := "1.0"

scalaVersion := "2.11.7"

//unmanagedBase := baseDirectory.value / "lib"

//unmanagedJars in Compile := (baseDirectory.value ** "*.jar").classpath

val botDirectory = SettingKey[File]("bot-directory")

val scalatronFile = SettingKey[File]("scalatron-file")

val play = TaskKey[Unit]("play")

val log: ProcessLogger = new ProcessLogger {
  def info (s: => String) {println(s)}
  def error (s: => String) { println(s)}
  def buffer[T] (f: => T): T = f
}

botDirectory := file("bots")

scalacOptions in Test ++= Seq("-Yrangepos")

publish <<= (botDirectory, name, javaOptions/*, scalatronFile in Compile*/, Keys.`package` in Compile) map { (bots, name, javaOptions, /*ucp,*/ botJar) =>
  println("packaging...")
  IO createDirectory (bots / name)
  println("copying...")
  IO copyFile (botJar, bots / name / "ScalatronBot.jar")

}
