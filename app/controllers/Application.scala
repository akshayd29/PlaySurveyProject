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
      val userServiceObj = new UserService
      val userList : List[User] = userServiceObj.filterUser(email)(session)
      val qaServiceObj = new QuestionAnswerService
      val answerList : List[Answers] = qaServiceObj.filterAnswers(email)(session)

      Ok(views.html.surveyAnswers(userList, answerList))
    }
  }
}
