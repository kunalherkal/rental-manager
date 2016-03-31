package dao

import com.google.inject.{Inject, Singleton}
import models.Apartment
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.lifted.ProvenShape

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._

/**
  * Created by khn3193 on 3/31/16.
  */
@Singleton
class ApartmentDaoImpl @Inject()(databaseConfigProvider: DatabaseConfigProvider) extends ApartmentDao {

  val dbConfig = databaseConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  class ApartmentTable(tag : Tag) extends Table[Apartment](tag, "apartments") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def rooms = column[Int]("rooms")
    def area = column[BigDecimal]("area")

    override def * : ProvenShape[Apartment] = (id, name, rooms, area) <> (Apartment.tupled, Apartment.unapply)
  }

  implicit val apartments = TableQuery[ApartmentTable]

  override def add(apartment: Apartment): Future[String] = {
    db.run(apartments += apartment).map(res => "Apartment Successfully Added").recover {
      case ex : Exception => ex.getCause.getMessage
    }
  }

  override def get(id: Long): Future[Option[Apartment]] = db.run(apartments.filter(_.id === id).result.headOption)


  override def delete(id: Long): Future[Int] = db.run(apartments.filter(_.id === id).delete)


  override def listAll: Future[Seq[Apartment]] = db.run(apartments.result)

}
