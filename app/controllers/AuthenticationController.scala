package controllers

import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Controller, Action}
import services.{AdminService}
import views.html


/**
  * Created by khn3193 on 4/3/16.
  */
class AuthenticationController @Inject()(adminService: AdminService, val messagesApi: MessagesApi) extends Controller with I18nSupport {

  val loginForm = Form(
    tuple(
      "email" -> text,
      "password" -> text
    ) verifying ("Invalid email or password", result => result match {
      case (email, password) => adminService.authenticate(email, password).isDefined
      case _ => false
    })
  )

  def login = Action { implicit request =>
    Ok(html.login(loginForm))
  }

  def authenticate = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.login(formWithErrors)),
      user => Redirect(routes.ApartmentController.dashboard).withSession("email" -> user._1))
  }

  def logout = Action {
    Redirect(routes.AuthenticationController.login).withNewSession.flashing(
      "success" -> "You've been logged out")
  }
}
