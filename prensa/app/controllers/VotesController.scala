package controllers

import javax.inject._

import models.Vote
import models.DiputadoNoId
import play.api.libs.json._
import play.api.mvc._
import play.modules.reactivemongo._
import reactivemongo.api.ReadPreference
import reactivemongo.play.json._
import reactivemongo.play.json.collection._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by luckio on 17-05-17.
  */
@Singleton
class VotesController @Inject()(val reactiveMongoApi: ReactiveMongoApi)(implicit exec: ExecutionContext) extends Controller with MongoController with ReactiveMongoComponents {

  def postsFuture: Future[JSONCollection] = database.map(_.collection[JSONCollection]("votos"))

  def index() = Action.async {
    val futurePostsList: Future[List[Vote]] = postsFuture.flatMap { _
        .find(Json.obj())
        .sort(Json.obj("created" -> -1))
        .cursor[Vote](ReadPreference.primary)
        .collect[List]()
    }

    futurePostsList.map { diputados =>
      Ok(Json.toJson(diputados))
    }
  }

  def all() = Action.async {
    val futurePostsList: Future[List[Vote]] = postsFuture.flatMap { _
        .find(Json.obj())
        .sort(Json.obj("created" -> -1))
        .cursor[Vote](ReadPreference.primary)
        .collect[List]()
    }

    futurePostsList.map { diputados =>
      Ok("callback("+Json.toJson(diputados)+");").as("application/javascript");
    }
  }

  def findByDiputadoId(id: Int) = Action.async {
    val futurePostsList: Future[List[Vote]] = postsFuture.flatMap { _
        .find(Json.obj("diputado_id" -> id))
        .sort(Json.obj("created" -> -1))
        .cursor[Vote](ReadPreference.primary)
        .collect[List]()
    }

    futurePostsList.map { diputados =>
      Ok("callback("+Json.toJson(diputados)+");").as("application/javascript");
    }
  }

  def findByDiputadoName(name: String) = Action.async {
    val futurePostsList: Future[List[Vote]] = postsFuture.flatMap { _
        .find(Json.obj("nombre" -> name))
        .sort(Json.obj("created" -> -1))
        .cursor[Vote](ReadPreference.primary)
        .collect[List]()
    }

    futurePostsList.map { diputados =>
      Ok("callback("+Json.toJson(diputados)+");").as("application/javascript");
    }
  }

}
