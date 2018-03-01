package org.sample

class Movie {

    Long myId
    String budget
    String homepage
    String originalLanguage
    String originalTitle
    String overview
    String popularity
    String releaseDate
    String revenue
    String runtime
    String status
    String tagline
    String title
    String voteAverage
    String voteCount

    Set<Genre> genres
    Set<Keyword> keywords
    Set<ProductionCompany> productionCompanies
    Set<ProductionCountry> productionCountries
    Set<SpokenLanguage> spokenLanguages

    static hasMany = [genres:Genre, keywords: Keyword, productionCompanies: ProductionCompany,
                      productionCountries: ProductionCountry, spokenLanguages: SpokenLanguage]

    static constraints = {
        originalTitle       nullable: false
        releaseDate         nullable: true
        revenue             nullable: true
        voteAverage         nullable: true
        tagline             nullable: true
        budget              nullable: true
        homepage            nullable: true
        originalLanguage    nullable: true
        overview            nullable: true
        popularity          nullable: true
        runtime             nullable: true
        status              nullable: true
        title               nullable: true
        voteCount           nullable: true
        myId                nullable: false
    }

    static mapping = {
        overview type: "text"   // bigger then VARCHAR(255)
        tagline type: "text"
    }

    public String toString(){
        originalTitle + " (" + releaseDate + ")"
    }
}
