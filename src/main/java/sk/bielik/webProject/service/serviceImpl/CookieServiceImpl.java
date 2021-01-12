package sk.bielik.webProject.service.serviceImpl;

import org.springframework.stereotype.Service;
import sk.bielik.webProject.entity.TrolleyItem;
import sk.bielik.webProject.entityDto.CustomerDto;
import sk.bielik.webProject.entityDto.TrolleyItemDto;
import sk.bielik.webProject.service.CookieService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Service
public class CookieServiceImpl implements CookieService {
    @Override
    public void addTrolleyCookie(CustomerDto customerDto, String cookieName, int maxCookieAge, HttpServletResponse response) {
        List<TrolleyItemDto> cookieTrolley=customerDto.getTrolley().getTrolleyItems();
        String cookieStringValue="";
        for (TrolleyItemDto trolleyItem1:cookieTrolley){
            cookieStringValue=cookieStringValue+trolleyItem1.getId()+"-";
        }


        Cookie cookie=new Cookie(cookieName,cookieStringValue);
        cookie.setMaxAge(maxCookieAge);
        response.addCookie(cookie);
    }

    @Override
    public void terminateCookie(String cookieName,HttpServletResponse response) {
        Cookie cookie=new Cookie(cookieName,"");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
