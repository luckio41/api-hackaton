package models

import play.api.libs.json.Json

/**
  * Created by luckio on 15-05-17.
  */
case class Vote (vote: String, 
                    diputado_id: Int,
                    votacion_id: Int,
                    motivo_ausencia: String,
                    nombre: String,
                    comite: String,
                    img_url: String,
                    fecha_namcimiento: String,
                    profesion: String,
                    region: String,
                    distrito: Int,
                    partido: String,
                    vote_partido: String)

object Vote {
  implicit val formatter = Json.format[Vote]
}