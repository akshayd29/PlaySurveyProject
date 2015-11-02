package services

import controllers.QuestionAnswer._
import models.Answers
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.Session

class QuestionAnswerService {

  def insertAnswers(answer : Answers)(implicit session: Session) = {
    answers.insert(answer)
  }

  def filterAnswers(email : String)(implicit session: Session) : List[Answers] = {
    val answerList : List[Answers] = answers.list.filter(_.email == email)
    answerList
  }
}
