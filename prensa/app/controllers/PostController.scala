package controllers

import javax.inject._
import models.Post
import play.api.libs.json._
import play.api.mvc._
import play.modules.reactivemongo._
import reactivemongo.api.ReadPreference
import reactivemongo.play.json._
import reactivemongo.play.json.collection._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class PostController @Inject()(val reactiveMongoApi: ReactiveMongoApi)(implicit exec: ExecutionContext) extends Controller with MongoController with ReactiveMongoComponents {

  def postsFuture: Future[JSONCollection] = database.map(_.collection[JSONCollection]("post"))

  def index(primary: String, secondary: String, year: String, mount: String, day: String, post_name: String) = Action.async {
    val futurePostsList: Future[List[Post]] = postsFuture.flatMap { _
      .find(Json.obj(
        "primary" -> primary,
        "secondary" -> secondary,
        "year" -> year,
        "mount" -> mount,
        "day" -> day,
        "post_name" -> post_name
      ))
      .sort(Json.obj("created" -> -1))
      .cursor[Post](ReadPreference.primary)
      .collect[List]()
    }

    futurePostsList.map { posts =>
      if (!posts.isEmpty) {
        Ok(views.html.post.index(posts))
      } else {
        Redirect("/wp-login")
      }
    }
  }
}