package services

import models.{Answers, User}
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.Session
import controllers.Application._

class AppService {

  def insertUser(user : User)(implicit session: Session) = {
    users.insert(user)
  }

  def insertAnswers(answer : Answers)(implicit session: Session) = {
    answers.insert(answer)
  }

  def filterUser(email : String)(implicit session: Session) : List[User] = {
    val userList : List[User] = users.list.filter(_.email == email)
    userList
  }

  def filterAnswers(email : String)(implicit session: Session) : List[Answers] = {
    val answerList : List[Answers] = answers.list.filter(_.email == email)
    answerList
  }

}
