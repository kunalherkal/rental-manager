package services

import com.google.inject.ImplementedBy
import models.Tenant

import scala.concurrent.Future

/**
  * Created by khn3193 on 3/30/16.
  */

@ImplementedBy(classOf[TenantServiceImpl])
trait TenantService {
  def addTenant(tenant:Tenant) : Future[String]
  def getTenant(id : Long) : Future[Option[Tenant]]
  def deleteTenant(id : Long) : Future[Int]
  def listAllTenants : Future[Seq[Tenant]]
}
