package org.sample

class ProductionCompany {

    Long myId
    String name

    static belongsTo = Movie
    static hasMany = [movies: Movie]

    static constraints = {
        name nullable: false
    }

    public String toString(){
        name
    }
}
