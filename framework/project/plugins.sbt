logLevel := Level.Warn

resolvers += Classpaths.typesafeResolver

resolvers += "Traveloka Local Artifactory Repo" at "http://traveloka-t1.local:8081/artifactory/repo/"

resolvers += "Traveloka Local Repo Maven" at "http://traveloka-localrepo/maven/"

resolvers += "Traveloka Local Repo Ivy" at "http://traveloka-localrepo/ivy/"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.sbtscalariform" % "sbtscalariform" % "0.3.0")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.0.0")
