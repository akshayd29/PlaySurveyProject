package controllers

import models.{User,UserTable}
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.DBAction
import play.api.mvc.Controller
import services.{UserService}
import scala.slick.lifted.TableQuery

object UserController extends Controller{

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
          UserService.insertUser(user)(session)
          Ok(views.html.shareLinkPage(user))
        })
    }
  }

  def surveyQuestions(email : String) = DBAction {
    implicit request => {
      val session = request.dbSession
      val userList : List[User] = UserService.filterUser(email)(session)
      Ok(views.html.surveyQuestions(userList))
    }
  }

  def updateUserForm(email : String) = DBAction {
    implicit request => {
      val session = request.dbSession
      val userList: List[User] = UserService.filterUser(email)(session)
      Ok(views.html.userUpdateForm(userList))
    }
  }

  def updatedUserQuestions = DBAction{
    implicit request => {
      val session = request.dbSession
      userForm.bindFromRequest.fold(
        formWithErrors => {
          BadRequest(views.html.main())
        },
        updatedUser => {
          val updated = userForm.bindFromRequest.get
          UserService.updateUser(updated)(session)
          Ok(views.html.shareLinkPage(updated))
        }
      )
    }
  }
}
