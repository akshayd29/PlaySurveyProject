package models

import play.api.db.slick.Config.driver.simple._

case class User(name : String, start : String, stop : String, continue : String, email : String)

class UserTable(tag: Tag) extends Table[User](tag, "USER") {

  def name = column[String]("name", O.NotNull)
  def start = column[String]("q1", O.NotNull)
  def stop = column[String]("q2", O.NotNull)
  def continue = column[String]("q3", O.NotNull)
  def email = column[String]("email", O.NotNull)

  def * = (name, start, stop, continue, email) <> (User.tupled, User.unapply _)
}