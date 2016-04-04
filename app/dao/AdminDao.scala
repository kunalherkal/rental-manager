package dao

import com.google.inject.ImplementedBy
import models.Admin

import scala.concurrent.Future

/**
  * Created by khn3193 on 4/3/16.
  */
@ImplementedBy(classOf[AdminDaoImpl])
trait AdminDao {
  def findByEmail(email : String) : Future[Option[Admin]]
  def listAll : Future[Seq[Admin]]
  def authenticate(email: String, password : String): Option[Admin]
}
