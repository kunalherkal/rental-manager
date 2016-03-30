package services

import javax.inject.Inject

import com.google.inject.Singleton
import dao.TenantDao
import models.Tenant

import scala.concurrent.Future

/**
  * Created by khn3193 on 3/30/16.
  */

@Singleton
class TenantServiceImpl @Inject()(tenantDao: TenantDao) extends TenantService{

  override def addTenant(tenant: Tenant): Future[String] = tenantDao.add(tenant)

  override def listAllTenants: Future[Seq[Tenant]] = tenantDao.listAll

  override def deleteTenant(id: Long): Future[Int] = tenantDao.delete(id)

  override def getTenant(id: Long): Future[Option[Tenant]] = tenantDao.get(id)
}
