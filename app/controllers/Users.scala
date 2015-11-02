package controllers

import models.{User,UserTable}
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.DBAction
import play.api.mvc.Controller
import services.{UserService}
import scala.slick.lifted.TableQuery

object Users extends Controller{

  val users = TableQuery[UserTable]

  val userForm = Form(mapping(
    "name" -> nonEmptyText,
    "start" -> text,
    "stop" -> text,
    "continue" -> text,
    "email" -> nonEmptyText)(User.apply)(User.unapply))


  def insertUserQuestions = DBAction {
    implicit request => {
      val session = request.dbSession

      userForm.bindFromRequest.fold(
        formWithErrors => {
          BadRequest(views.html.main())
        },
        user => {
          val user = userForm.bindFromRequest.get
          val as = new UserService
          as.insertUser(user)(session)

          Ok(views.html.shareLinkPage(user))
        })
    }
  }

  def surveyQuestions(email : String) = DBAction {
    implicit request => {
      val session = request.dbSession
      val as = new UserService
      val userList : List[User] = as.filterUser(email)(session)

      Ok(views.html.surveyQuestions(userList))
    }
  }


}