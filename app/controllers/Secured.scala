package controllers

import play.api.mvc._

import scala.concurrent.Future

/**
  * Created by khn3193 on 4/3/16.
  */
trait Secured {
  self: Controller =>

  private def username(request: RequestHeader) = request.session.get("email")

  private def onUnauthorized(request: RequestHeader) = {
    Results.Redirect(routes.AuthenticationController.login)
  }

  def IsAuthenticated(f: => String => Request[AnyContent] => Future[Result]) = {
    Security.Authenticated(username, onUnauthorized) { user =>
      Action.async(request => f(user)(request))
    }
  }
}
