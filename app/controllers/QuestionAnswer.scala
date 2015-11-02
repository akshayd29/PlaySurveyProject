package controllers

import models.{Answers, AnswersTable}
import play.api.data.Form
import play.api.data.Forms._
import play.api.db.slick.DBAction
import play.api.mvc.Controller
import services.{QuestionAnswerService}
import scala.slick.lifted.TableQuery

object QuestionAnswer extends Controller{

  val answers = TableQuery[AnswersTable]

  val answerForm = Form(mapping(
    "name" -> nonEmptyText,
    "email" -> nonEmptyText,
    "startQuestion" -> text,
    "stopQuestion" -> text,
    "continueQuestion" -> text
  )(Answers.apply)(Answers.unapply))


  def doneAnswering = DBAction {
    implicit request => {
      val session = request.dbSession

      answerForm.bindFromRequest.fold(
        formWithErrors => {
          BadRequest(views.html.finalPage("Error"))
        },
        answers => {
          val answer = answerForm.bindFromRequest.get
          val qaServiceObj = new QuestionAnswerService
          qaServiceObj.insertAnswers(answer)(session)

          Ok(views.html.finalPage("Survey Completed Successfully..!"))
        }
      )
    }
  }


}
