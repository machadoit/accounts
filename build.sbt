val root = (
  project in file(".")
    configs UiTest.UiTest
    settings Common.settings
    enablePlugins(Git.plugins: _*)
    settings Git.settings
    settings Run.settings
    settings UiTest.settings
    enablePlugins(Native.plugins: _*)
    settings Native.settings
    settings Dependencies.settings
    settings(libraryDependencies ++= Dependencies.dependencies)
)


