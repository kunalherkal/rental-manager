package services

import com.google.inject.{Inject, Singleton}
import dao.AdminDao
import models.Admin

import scala.concurrent.Future

/**
  * Created by khn3193 on 4/3/16.
  */
@Singleton
class AdminServiceImpl @Inject()(adminDao: AdminDao) extends AdminService{

  override def findAdminByEmail(email: String): Future[Option[Admin]] = adminDao.findByEmail(email)

  override def authenticate(email: String, password: String): Option[Admin] = adminDao.authenticate(email, password)

  override def listAllAdmin: Future[Seq[Admin]] = adminDao.listAll
}
