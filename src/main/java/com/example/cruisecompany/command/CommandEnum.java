package com.example.cruisecompany.command;

import com.example.cruisecompany.command.implementation.*;

public enum CommandEnum {

    EXIT(new actionExit()),
    INDEX(new pageIndex()),
    LOGIN(new pageLogin()),
    LOGIN_USER(new actionLogin()),
    SHIPS(new pageShips()),
    USERS(new pageUsers()),
    ORDERS(new pageOrders()),
    CRUISES(new pageCruises()),
    REGISTRATION_NEW_USER(new actionRegistrationNewUser()),
    REGISTRATION_PAGE(new pageRegistration()),
    ADD_SHIP(new pageAddShip()),
    ADD_NEW_SHIP(new actionAddNewShip()),
    ADD_CRUISE(new pageAddCruise()),
    ADD_NEW_CRUISE(new actionAddNewCruise()),
    BUY_CRUISE(new pageBuyCruise()),
    CART(new pageCart()),
    CRUISE_DETAILED(new pageCruiseInCartDetailed()),
    ERROR_404(new pageError404());


    private OpenPage page;

    CommandEnum(OpenPage page) {
        this.page = page;
    }

    public OpenPage getPage() {
        return page;
    }
}
