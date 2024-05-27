package com.task.jwt.security.exception;


public class InValidToken extends ApplicationException {
    public InValidToken(String porOrgacode, ErrorMessage errorCode, Object... arguments) {
        super(porOrgacode, ERRCode.FILE_CURRUPTED, arguments);
    }
}
