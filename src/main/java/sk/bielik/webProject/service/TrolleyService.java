package sk.bielik.webProject.service;

import sk.bielik.webProject.request.AddProductToTrolleyRequest;
import sk.bielik.webProject.response.AddProductToTrolleyResponse;

public interface TrolleyService {

    AddProductToTrolleyResponse addProductToTrolley(AddProductToTrolleyRequest addProductToTrolleyRequest);
}
