package controllers

import models._
import play.api.db.slick.DBAction
import play.api.mvc.{Action, Controller}
import services.{QuestionAnswerService, UserService}

object Application extends Controller {

  def index = Action {
    Ok(views.html.main())
  }

  def surveyAnswers(email : String) = DBAction{
    implicit request => {
      val session = request.dbSession
      val userList : List[User] = UserService.filterUser(email)(session)
      val answerList : List[Answers] = QuestionAnswerService.filterAnswers(email)(session)

      Ok(views.html.surveyAnswers(userList, answerList))
    }
  }
}
