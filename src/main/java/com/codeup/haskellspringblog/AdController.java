package com.codeup.haskellspringblog;

public class AdController {
    private final AdRepository adDao;

    public AdController(AdRepository adDao) {
        this.adDao = adDao;
    }

    //In Java, before we can use methods of other classes, we first need to create the object of that class (i.e. class A needs to create an instance of class B). One of the ways we can do this, is in the constructor. Spring provides us with some facilities to use automatic dependency injection in our classes. The following example shows how we can inject AdRepository into our AdController.
}
