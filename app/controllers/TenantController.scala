package controllers

import com.google.inject.Inject
import models.{Tenant, TenantForm}
import play.api.i18n.{Messages, I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}
import services.TenantService
import play.api.libs.concurrent.Execution.Implicits._

import scala.concurrent.Future

/**
  * Created by khn3193 on 3/30/16.
  */
class TenantController @Inject()(tenantService: TenantService, val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def dashboard = Action.async { implicit request =>
    tenantService.listAllTenants map {
      tenants => Ok(views.html.tenant(TenantForm.form, tenants))
    }
  }

  def addTenant() = Action.async { implicit request =>
    TenantForm.form.bindFromRequest.fold(
      errorForm => Future.successful(Ok(views.html.tenant(errorForm, Seq.empty[Tenant]))),
      data => {
        val newTenant = Tenant(0, data.firstName, data.lastName, data.mobile, data.email)
        tenantService.addTenant(newTenant).map(res =>
          Redirect(routes.TenantController.dashboard()).flashing(Messages("flash.success") -> res)
        )
    })

  }

  def deleteTenant(id: Long) = Action.async { implicit request =>
    tenantService.deleteTenant(id) map { res =>
      Redirect(routes.TenantController.dashboard())

    }

  }

}
