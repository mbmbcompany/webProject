package sk.bielik.webProject.database;

import sk.bielik.webProject.entity.Customer;

import java.util.HashMap;
import java.util.Map;

public class OnlineCustomers {

    private static OnlineCustomers onlineCustomers =null;

    private Map<String, Customer> map=new HashMap<String, Customer>();

    private OnlineCustomers() {
    }

    public static OnlineCustomers createOrGetOnlineUsers(){
        if (onlineCustomers ==null){
            onlineCustomers =new OnlineCustomers();
        }
        return onlineCustomers;
    }

    public Map<String, Customer> getMap() {
        return map;
    }

    public void setMap(Map<String, Customer> map) {
        this.map = map;
    }
}
