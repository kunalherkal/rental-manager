package services

import com.google.inject.{Inject, Singleton}
import dao.ApartmentDao
import models.Apartment

import scala.concurrent.Future

/**
  * Created by khn3193 on 3/31/16.
  */
@Singleton
class ApartmentServiceImpl @Inject()(apartmentDao : ApartmentDao) extends ApartmentService{

  override def addApartment(apartment: Apartment): Future[String] = apartmentDao.add(apartment)

  override def listAllApartments: Future[Seq[Apartment]] = apartmentDao.listAll

  override def deleteApartment(id: Long): Future[Int] = apartmentDao.delete(id)

  override def getApartment(id: Long): Future[Option[Apartment]] = apartmentDao.get(id)
}
