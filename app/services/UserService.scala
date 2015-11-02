package services

import controllers.Users._
import models.User
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.Session

class UserService {

  def insertUser(user : User)(implicit session: Session) = {
    users.insert(user)
  }

  def filterUser(email : String)(implicit session: Session) : List[User] = {
    val userList : List[User] = users.list.filter(_.email == email)
    userList
  }
}
