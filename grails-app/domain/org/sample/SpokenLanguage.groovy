package org.sample

class SpokenLanguage {

    String shortName
    String name

    static belongsTo = Movie
    static hasMany = [movies: Movie]

    static constraints = {
        shortName   nullable: false
        name        nullable: true
    }

    public String toString(){
        shortName
    }
}
