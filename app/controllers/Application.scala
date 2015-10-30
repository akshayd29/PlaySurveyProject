package controllers


import models._
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.DBAction
import play.api.mvc.{Action, Controller}
import services.AppService
import scala.slick.lifted.TableQuery

object Application extends Controller {

  def index = Action {
    Ok(views.html.main())
  }

  val users = TableQuery[UserTable]

  val userForm = Form(mapping(
    "name" -> nonEmptyText,
    "start" -> text,
    "stop" -> text,
    "continue" -> text,
    "email" -> nonEmptyText)(User.apply)(User.unapply))

  val answers = TableQuery[AnswersTable]

  val answerForm = Form(mapping(
    "name" -> nonEmptyText,
    "email" -> nonEmptyText,
    "startQuestion" -> text,
    "stopQuestion" -> text,
    "continueQuestion" -> text
    )(Answers.apply)(Answers.unapply))


  def insertQuestions = DBAction {
    implicit request => {
      val session = request.dbSession
      val user = userForm.bindFromRequest.get
      val as = new AppService
      as.insertUser(user)(session)

      Ok(views.html.shareLinkPage(user))
    }
  }

  def surveyQuestions(email : String) = DBAction {
    implicit request => {
      val session = request.dbSession
      val as = new AppService
      val userList : List[User] = as.filterUser(email)(session)

      Ok(views.html.surveyQuestions(userList))
    }
  }

  def surveyAnswers(email : String) = DBAction{
    implicit request => {
      val session = request.dbSession
      val as = new AppService
      val userList : List[User] = as.filterUser(email)(session)
      val answerList : List[Answers] = as.filterAnswers(email)(session)

      Ok(views.html.surveyAnswers(userList, answerList))
    }
  }

  def doneAnswering = DBAction {
    implicit request => {
      val session = request.dbSession
      val answer = answerForm.bindFromRequest.get
      val as = new AppService
      as.insertAnswers(answer)(session)

      Redirect(routes.Application.index)
    }

  }

}
