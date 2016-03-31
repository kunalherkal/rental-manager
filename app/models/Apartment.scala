package models

import play.api.data.Form
import play.api.data.Forms._

/**
  * Created by khn3193 on 3/31/16.
  */
case class Apartment (id: Long, name: String, rooms: Int, area: BigDecimal)

case class ApartmentFormData(name: String, rooms: Int, area: BigDecimal)

object ApartmentForm {
  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "rooms" -> number,
      "area" -> bigDecimal
    )(ApartmentFormData.apply)(ApartmentFormData.unapply)
  )
}