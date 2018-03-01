package org.sample

class ProductionCountry {

    String shortName
    String name

    static belongsTo = Movie
    static hasMany = [movies: Movie]

    static constraints = {
        shortName   nullable: false
        name        nullable: false
    }

    public String toString(){
        name
    }
}
