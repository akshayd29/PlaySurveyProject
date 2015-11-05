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
 }
