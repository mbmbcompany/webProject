package sk.bielik.webProject.service;

import sk.bielik.webProject.entityDto.CustomerDto;

import javax.servlet.http.HttpServletResponse;

public interface CookieService {

    void addTrolleyCookie(CustomerDto customerDto, String cookieName, int maxCookieAge, HttpServletResponse response);

    void terminateCookie(String cookieName,HttpServletResponse response);
}
