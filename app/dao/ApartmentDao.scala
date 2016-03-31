package dao

import com.google.inject.ImplementedBy
import models.Apartment

import scala.concurrent.Future

/**
  * Created by khn3193 on 3/31/16.
  */
@ImplementedBy(classOf[ApartmentDaoImpl])
trait ApartmentDao {

  def add(apartment : Apartment) : Future[String]
  def get(id : Long) : Future[Option[Apartment]]
  def delete(id : Long) : Future[Int]
  def listAll : Future[Seq[Apartment]]
}
