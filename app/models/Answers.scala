package models

import play.api.db.slick.Config.driver.simple._

case class Answers(name : String, email : String, startQuestion : String, stopQuestion : String, continueQuestion : String)

class AnswersTable(tag : Tag) extends Table[Answers](tag, "ANSWERS"){

  def name = column[String]("name", O.NotNull)
  def email = column[String]("email", O.NotNull)
  def startQuestion = column[String]("startQuestion", O.NotNull)
  def stopQuestion = column[String]("stopQuestion", O.NotNull)
  def continueQuestion = column[String]("continueQuestion", O.NotNull)


  def * = (name, email, startQuestion, stopQuestion, continueQuestion) <> (Answers.tupled, Answers.unapply _)
}
