# Cinema API

## Requirements

* [Docker](https://docs.docker.com/get-docker/)
* [Docker compose](https://docs.docker.com/compose/install/)
* [OMDb API key](http://www.omdbapi.com/apikey.aspx)

## Running the app

Supply the OMDb API key with environment variable, and run the app with `docker-compose`:
```
$ OMDB_APIKEY=xxx docker-compose up
```

## API Documentation

You can browse the auto-generated docs after running the app: 
<http://localhost:8080/swagger-ui/>

## OpenAPI Specification

Auto-generated OpenAPI specification:
* v2 <http://localhost:8080/v2/api-docs>
* v3 <http://localhost:8080/v3/api-docs>

## Simplifications

* There is no authentication to the API
* Values coming from OMDb API are not cached
* Entity IDs are client-generated
* Some inputs have missing validation, i.e. user can leave rating of any decimal value
* Tests are very basic. There are no tests of edge-cases or complex scenarios.
