package sk.bielik.webProject.service;

import sk.bielik.webProject.request.*;
import sk.bielik.webProject.response.*;

public interface SignInService {

    SignInResponse signIn(SignInRequest signInRequest);
}
