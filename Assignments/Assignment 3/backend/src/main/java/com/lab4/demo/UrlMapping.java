package com.lab4.demo;

public class UrlMapping {
    public static final String API_PATH = "/api";
    public static final String CLINIC = API_PATH + "/clinic";
    public static final String ENTITY = "/{id}";

    public static final String AUTH = API_PATH + "/auth";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";

    public static final String USERS = API_PATH + "/users";

    public static final String PATIENT = CLINIC + "/patient";
    public static final String CONSULTATION = CLINIC + "/consultation";

}
