package accounts.view

import com.typesafe.scalalogging.StrictLogging
import io.scalatestfx.api.SfxRobot
import io.scalatestfx.framework.scalatest.JFXAppFixture
import org.scalatest.WordSpec

trait ViewTest extends WordSpec with SfxRobot with JFXAppFixture with ViewMatchers with StrictLogging {

  Thread.setDefaultUncaughtExceptionHandler { (t, e) =>
    logger.error(s"Uncaught exception in thread '$t':", e)
  }
}
