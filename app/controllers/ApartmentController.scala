package controllers

import com.google.inject.Inject
import models.{Apartment, ApartmentForm}
import play.api.i18n.{Messages, I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}
import services.ApartmentService

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._

/**
  * Created by khn3193 on 3/31/16.
  */
class ApartmentController @Inject()(apartmentService: ApartmentService, val messagesApi: MessagesApi) extends Controller with I18nSupport{

  def dashboard = Action.async { implicit request =>
    apartmentService.listAllApartments map {
      apartments => Ok(views.html.apartment(ApartmentForm.form, apartments))
    }
  }

  def addApartment() = Action.async { implicit request =>
    ApartmentForm.form.bindFromRequest.fold(
      errorForm => Future.successful(Ok(views.html.apartment(errorForm, Seq.empty[Apartment]))),
      data => {
        val newApartment = Apartment(0, data.name, data.rooms, data.area)
        apartmentService.addApartment(newApartment).map(res =>
          Redirect(routes.ApartmentController.dashboard()).flashing(Messages("flash.success") -> res)
        )
      })

  }

  def deleteApartment(id: Long) = Action.async { implicit request =>
    apartmentService.deleteApartment(id) map { res =>
      Redirect(routes.ApartmentController.dashboard())

    }

  }
}
