/*
 * Copyright (c) 2018. For PheIot Group.
 */

package com.pheiot.phecloud.pd.openapi;

import com.pheiot.phecloud.pd.openapi.exception.BusinessException;

public class SecurityPolice {
    public final static String HTTP_HEADER_USER_TOKEN = "phe-application-user-token";

    public static void checkUserToken(String token) {
        if (token == null) {
            throw new BusinessException("No token in request");
        }
    }
}
