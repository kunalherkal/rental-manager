package models

/**
  * Created by khn3193 on 3/30/16.
  */

import play.api.data.Form
import play.api.data.Forms._

case class Tenant(id : Long,firstName : String, lastName : String , mobile : Long , email : String)

case class TenantFormData(firstName : String, lastName : String , mobile : Long , email : String )

object TenantForm {

  val form = Form(
    mapping(
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "mobile" -> longNumber,
      "email" -> email
    )(TenantFormData.apply)(TenantFormData.unapply)
  )
}