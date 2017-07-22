package models

import play.api.libs.json.Json

/**
  * Created by luckio on 15-05-17.
  */
case class Post (title: String,
                 post_content: String,
                 extract: String,
                 post_name: String,
                 primary: String,
                 secondary: String,
                 year: String,
                 mount: String,
                 day: String)

object Post {
  implicit val formatter = Json.format[Post]
}