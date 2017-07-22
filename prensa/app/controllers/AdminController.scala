package controllers

import javax.inject._
import play.api.mvc._


@Singleton
class AdminController extends Controller {

  def index = Action { implicit request =>
    Redirect("/wp-login")
  }

  def login = Action { implicit request =>
    Ok(views.html.admin.index())
  }
}