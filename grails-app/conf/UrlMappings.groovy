class UrlMappings {

  static mappings = {
    "/$controller/$action?/$id?(.$format)?" {
      constraints {
        // apply constraints here
      }
    }

    "/hoodfiller"(controller: 'rest', action: 'hoodFiller')

    "/"(view: "/index")
    "500"(view: '/error')
  }
}
