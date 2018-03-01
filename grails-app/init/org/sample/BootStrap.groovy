package org.sample

import grails.converters.JSON
import grails.util.Environment

class BootStrap {

    def grailsApplication

    def init = { servletContext ->
        println "starting bootstrapping"
        def t = System.currentTimeMillis()

        if (Environment.current == Environment.DEVELOPMENT){
            grailsApplication.parentContext.getResource("classpath:init/tmdb_5000_movies.csv").inputStream
                    .toCsvReader(['charset': 'UTF-8', 'separatorChar': ',', 'skipLines': 1, 'quoteChar':'"', 'escapeChar':'\\']).eachLine { tokens ->

                //budget,genres,homepage,id,keywords,original_language,original_title,overview,popularity,production_companies,
                def budget = tokens[0].trim()
                def genres = tokens[1].trim()
                def homepage = tokens[2].trim()
                def id = tokens[3].trim()
                def keywords = tokens[4].trim()
                def originalLanguage = tokens[5].trim()
                def originalTitle = tokens[6].trim()
                def overview = tokens[7].trim()
                def popularity = tokens[8].trim()
                def productionCompanies = tokens[9].trim()
                // production_countries,release_date,revenue,runtime,spoken_languages,status,tagline,title,vote_average,vote_count
                def productionCountries = tokens[10].trim()
                def releaseDate = tokens[11].trim()
                def revenue = tokens[12].trim()
                def runtime = tokens[13].trim()
                def spokenLanguages = tokens[14].trim()
                def status = tokens[15].trim()
                def tagline = tokens[16].trim()
                def title = tokens[17].trim()
                def voteAverage = tokens[18].trim()
                def voteCount = tokens[19].trim()
//                println "importing '${originalTitle}', produced by '${productionCompanies}', with keywords '${keywords}'"

                def movie = new Movie(budget: budget, homepage: homepage, myId: Long.parseLong(id), originalLanguage: originalLanguage,
                    originalTitle: originalTitle, overview: overview, popularity: popularity, releaseDate: releaseDate,
                    revenue: revenue, runtime: runtime, status: status,
                    tagline: tagline, title: title, voteAverage: voteAverage, voteCount: voteCount)

                // genre: [{"id": 28, "name": "Action"}, {"id": 12, "name": "Adventure"}, {"id": 14, "name": "Fantasy"}, {"id": 878, "name": "Science Fiction"}]
                JSON.parse(CsvJsonHelper.fixQuoting(genres as String)).collect { genre ->
                    Genre.findByMyId(genre.id)?: new Genre(myId: genre.id, name: genre.name).save(failOnError: true)
                }.each{ genre ->
                    movie.addToGenres(genre)
                }

                // [{"id": 1463, "name": "culture clash"}, {"id": 2964, "name": "future"}, {"id": 3386, "name": "space war"}, {"id": 3388, "name": "space colony"}, {"id": 3679, "name": "society"}, {"id": 3801, "name": "space travel"}, {"id": 9685, "name": "futuristic"}, {"id": 9840, "name": "romance"}, {"id": 9882, "name": "space"}, {"id": 9951, "name": "alien"}, {"id": 10148, "name": "tribe"}, {"id": 10158, "name": "alien planet"}, {"id": 10987, "name": "cgi"}, {"id": 11399, "name": "marine"}, {"id": 13065, "name": "soldier"}, {"id": 14643, "name": "battle"}, {"id": 14720, "name": "love affair"}, {"id": 165431, "name": "anti war"}, {"id": 193554, "name": "power relations"}, {"id": 206690, "name": "mind and soul"}, {"id": 209714, "name": "3d"}]
                JSON.parse(CsvJsonHelper.fixQuoting(keywords as String)).collect { keyword ->
                    Keyword.findByMyId(keyword.id)?: new Keyword(myId: keyword.id, name: keyword.name).save(failOnError: true)
                }.each{ keyword ->
                    movie.addToKeywords(keyword)
                }

                // [{"name": "Ingenious Film Partners", "id": 289}, {"name": "Twentieth Century Fox Film Corporation", "id": 306}, {"name": "Dune Entertainment", "id": 444}, {"name": "Lightstorm Entertainment", "id": 574}]
                JSON.parse(CsvJsonHelper.fixQuoting(productionCompanies as String)).collect { company ->
                    ProductionCompany.findByMyId(company.id)?: new ProductionCompany(myId: company.id, name: company.name).save(failOnError: true)
                }.each{ company ->
                    movie.addToProductionCompanies(company)
                }

                // [{"iso_3166_1": "US", "name": "United States of America"}, {"iso_3166_1": "GB", "name": "United Kingdom"}]
                JSON.parse(CsvJsonHelper.fixQuoting(productionCountries as String)).collect { country ->
                    String shortName = getShortName(country)
                    ProductionCountry.findByShortName(shortName) ?: new ProductionCountry(shortName: shortName, name: country.name).save(failOnError: true)
                }.each{ country ->
                    movie.addToProductionCountries(country)
                }

                // [{"iso_639_1": "en", "name": "English"}, {"iso_639_1": "es", "name": "Espa\u00f1ol"}]
                JSON.parse(CsvJsonHelper.fixQuoting(spokenLanguages as String)).collect { language ->
                    String shortName = getShortName(language)
                    SpokenLanguage.findByShortName(shortName) ?: new SpokenLanguage(shortName: shortName, name: language.name).save(failOnError: true)
                }.each{ language ->
                    movie.addToSpokenLanguages(language)
                }

                movie.save(failOnError: true)
            }
        }
        println "finished bootstrapping in ${System.currentTimeMillis() - t}ms"
    }

    private static String getShortName(def json){
        json.iso_3166_1 ?: json.iso_639_1
    }



    def destroy = {
    }
}
