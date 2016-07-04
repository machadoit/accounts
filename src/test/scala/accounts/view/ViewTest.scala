package accounts.view

import io.scalatestfx.api.SfxRobot
import io.scalatestfx.framework.scalatest.JFXAppFixture
import org.scalatest.WordSpec

trait ViewTest extends WordSpec with SfxRobot with JFXAppFixture with ViewMatchers
