package services

import com.google.inject.ImplementedBy
import models.Apartment

import scala.concurrent.Future

/**
  * Created by khn3193 on 3/31/16.
  */
@ImplementedBy(classOf[ApartmentServiceImpl])
trait ApartmentService {
  def addApartment(apartment: Apartment) : Future[String]
  def getApartment(id : Long) : Future[Option[Apartment]]
  def deleteApartment(id : Long) : Future[Int]
  def listAllApartments : Future[Seq[Apartment]]
}
