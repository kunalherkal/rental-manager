package dao

import com.google.inject.ImplementedBy
import models.Tenant

import scala.concurrent.Future

/**
  * Created by khn3193 on 3/30/16.
  */
@ImplementedBy(classOf[TenantDaoImpl])
trait TenantDao {

  def add(tenant : Tenant) : Future[String]
  def get(id : Long) : Future[Option[Tenant]]
  def delete(id : Long) : Future[Int]
  def listAll : Future[Seq[Tenant]]
}
