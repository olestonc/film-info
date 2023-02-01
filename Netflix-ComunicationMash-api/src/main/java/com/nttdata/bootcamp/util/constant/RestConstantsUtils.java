package com.nttdata.bootcamp.util.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RestConstantsUtils {

    public static final String APPLICATION_NAME = "/netflix";
    public static final String API_VERSION_1 = "/v1";

    public static final String SUCCESS = "Success";
    public static final String OK = "OK";

    public static final String RESOURCE_ACTOR = "/actor";
    public static final String RESOURCE_ACTORID = "/{actorId}";
    public static final String RESOURCE_ACTORWITHCHAPETERS = "/actorWithChapeters";

    public static final String RESOURCE_CATEGORY = "/category";
    public static final String RESOURCE_CATEGORYID = "/{categoryId}";

    public static final String RESOURCE_CHAPETER = "/chapeter";
    public static final String RESOURCE_CHAPETERID = "/{chapeterId}";

    public static final String RESOURCE_SEASON = "/season";
    public static final String RESOURCE_SEASONID = "/{seasonId}";
    
    public static final String RESOURCE_TVSHOW = "/tvshow";
    public static final String RESOURCE_TVSHOWID = "/{tvshowId}";

}
