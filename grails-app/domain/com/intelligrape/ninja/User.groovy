package com.intelligrape.ninja

class User {

    String name
    String address

    static constraints = {
        name(nullable: false)
        address(nullable: true, blank: true)
    }
}
