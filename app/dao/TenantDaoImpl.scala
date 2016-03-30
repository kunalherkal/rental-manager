package dao

import javax.inject.Inject
import com.google.inject.Singleton
import models.Tenant
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import play.api.libs.concurrent.Execution.Implicits._

import scala.concurrent.Future

/**
  * Created by khn3193 on 3/30/16.
  */

@Singleton
class TenantDaoImpl @Inject()(databaseConfigProvider: DatabaseConfigProvider) extends TenantDao {

  val dbConfig = databaseConfigProvider.get[JdbcProfile]

  import dbConfig._
  import driver.api._

  class TenantTable(tag : Tag) extends Table[Tenant](tag, "tenants") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def firstName = column[String]("first_name")
    def lastName = column[String]("last_name")
    def mobile = column[Long]("mobile")
    def email = column[String]("email")

    override def * =
      (id, firstName, lastName, mobile, email) <> (Tenant.tupled, Tenant.unapply)
  }

  implicit val tenants = TableQuery[TenantTable]

  override def add(tenant: Tenant): Future[String] = {
    db.run(tenants += tenant).map(res => "Tenant Successfully Added").recover {
      case ex : Exception => ex.getCause.getMessage
    }
  }

  override def get(id: Long): Future[Option[Tenant]] = {
    db.run(tenants.filter(_.id === id).result.headOption)
  }

  override def delete(id: Long): Future[Int] = {
    db.run(tenants.filter(_.id === id).delete)
  }

  override def listAll: Future[Seq[Tenant]] = {
    db.run(tenants.result)
  }
}
