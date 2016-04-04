package services

import com.google.inject.ImplementedBy
import models.Admin

import scala.concurrent.Future

/**
  * Created by khn3193 on 4/3/16.
  */
@ImplementedBy(classOf[AdminServiceImpl])
trait AdminService {
  def findAdminByEmail(email : String) : Future[Option[Admin]]
  def listAllAdmin : Future[Seq[Admin]]
  def authenticate(email: String, password : String): Option[Admin]
}
