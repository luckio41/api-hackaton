package models

import play.api.libs.json.Json

/**
  * Created by luckio on 15-05-17.
  */
case class Diputado (diputado_id: Int,
                 nombre: String,
                 comite: String,
                 img_url: String,
                 fecha_namcimiento: String,
                 profesion: String,
                 region: String,
                 distrito: Int,
                 partido: String)

object Diputado {
  implicit val formatter = Json.format[Diputado]
}