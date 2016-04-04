package dao

import com.google.inject.{Inject, Singleton}
import models.Admin
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.util.{Failure, Success}

/**
  * Created by khn3193 on 4/3/16.
  */
@Singleton
class AdminDaoImpl  @Inject()(databaseConfigProvider: DatabaseConfigProvider) extends AdminDao {

  val dbConfig = databaseConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  class AdminTable(tag : Tag) extends Table[Admin](tag, "admins") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def email = column[String]("email")
    def name = column[String]("name")
    def password = column[String]("password")

    override def * =
      (id, email, name, password) <> (Admin.tupled, Admin.unapply)
  }

  implicit val admins = TableQuery[AdminTable]

  override def findByEmail(email: String): Future[Option[Admin]] = {
    db.run(admins.filter(_.email === email).result.headOption)
  }

  override def authenticate(email: String, password : String): Option[Admin] = {
    val res = db.run(admins.filter(_.email === email).filter(_.password === password).result.headOption)

    Await.result(res, 1000 millis)
  }

  override def listAll: Future[Seq[Admin]] = db.run(admins.result)

}
