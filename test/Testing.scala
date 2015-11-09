
import play.api.test.{FakeRequest, WithApplication, PlaySpecification}

class Testing extends PlaySpecification{

  //Testing Controller
  "respond to the index Action" in new WithApplication{

    val result = controllers.Application.index()(FakeRequest())

    status(result) must beEqualTo(OK)
    contentType(result) must beSome("text/html")
    charset(result) must beSome("utf-8")
    contentAsString(result) must contain("Survey")
  }

  //Testing a template
  "render an HTML page" in new WithApplication() {
    val html = views.html.main()
    contentAsString(html) must contain("Survey")
  }

  "testing controller method" in new WithApplication{

    val res = controllers.Application.surveyAnswers("popo")(FakeRequest())

    status(res) must beEqualTo(OK)
    contentType(res) must beSome("text/html")
    contentAsString(res) must contain("Some answers to your questions")
  }

  "check response redirect" in new WithApplication{

  //routes.Application.surveyAnswers("kls").toString() must beEqualTo("/updateQ/surveyAns/email=kls")

    val request = FakeRequest(GET, "/surveyAns/email=popo")

    val response = route(request).get

    status(response) must be_==(OK)

  //  val result = controllers.Users.surveyQuestions("kls").apply(request)
  //  redirectLocation(result) must beSome(routes.QuestionAnswer.doneAnswering().url)
  }

 }
