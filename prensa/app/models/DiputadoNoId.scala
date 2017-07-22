package models

import play.api.libs.json.Json

/**
  * Created by luckio on 15-05-17.
  */
case class DiputadoNoId (nombre: String,
                    comite: String,
                    img_url: String,
                    fecha_namcimiento: String,
                    profesion: String,
                    region: String,
                    distrito: Int,
                    partido: String)

object DiputadoNoId {
  implicit val formatter = Json.format[DiputadoNoId]
}