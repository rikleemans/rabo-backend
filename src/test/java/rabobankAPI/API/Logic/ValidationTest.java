package rabobankAPI.API.Logic;

import org.junit.jupiter.api.Test;
import rabobankAPI.API.DTO.AppUserDTO;
import rabobankAPI.API.Model.AppUser;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    Validation validation = new Validation();
    @Test
    void validateUsernameHappyFlowTest() {
        AppUser appUser = new AppUser("Succeed", "ThisPasswordIsS@f3!");

        boolean result = validation.appUser(appUser);

        assertEquals(result, true);
    }

    @Test
    void validateUsernameHappyFlow2Test() {
        AppUser appUser = new AppUser("Succeed-Succeed", "ThisPasswordIsS@f3!");

        boolean result = validation.appUser(appUser);

        assertEquals(result, true);
    }

    @Test
    void validateUsernameSymbolFailTest() {
        AppUser appUser = new AppUser("Succeed$Succeed", "ThisPasswordIsS@f3!");

        boolean result = validation.appUser(appUser);

        assertEquals(!result, true);
    }

    //Failing Tests
    @Test
    void validateUsernameNoChapsTest() {
        AppUser appUser = new AppUser( "failfail", "ThisPasswordIsS@f3!");

        boolean result = validation.appUser(appUser);


        assertEquals(!result, true);
    }

    @Test
    void validateUsernameWrongMaxLengthTest() {
        // 50 max
        // 52 charts
        AppUser appUser = new AppUser( "Failfailfailfailfailfailfailfailfailfailfailfailfail", "ThisPasswordIsS@f3!");

        boolean result = validation.appUser(appUser);

        assertEquals(!result, true);
    }



    @Test
    void validateUsernameWrongMinLengthTest() {
        AppUser appUser = new AppUser( "", "ThisPasswordIsS@f3!");

        boolean result = validation.appUser(appUser);

        assertEquals(!result, true);
    }
    @Test
    void validatePasswordLength20CharTest() {
        AppUser appUser = new AppUser( "Test", "As1!Ew2!P9asdqwrok3%");

        boolean result = validation.appUser(appUser);

        assertEquals(result, true);
    }
    @Test
    void validatePasswordLength50CharTest() {
        AppUser appUser = new AppUser( "Test", "As1!Ew2!P9asdqwrok3%As1!Ew2!P9asdqwrok3%qwertyui87");

        boolean result = validation.appUser(appUser);

        assertEquals(result, true);
    }
    @Test
    void validatePasswordLength200CharTest() {
        AppUser appUser = new AppUser( "Test", "As1!Ew2!P9asdqwrok3%As1!Ew2!P9asdqwrok3%qwertyui87As1!Ew2!P9asdqwrok3%As1!Ew2!P9asdqwrok3%qwertyui87As1!Ew2!P9asdqwrok3%As1!Ew2!P9asdqwrok3%qwertyui87As1!Ew2!P9asdqwrok3%As1!Ew2!P9asdqwrok3%qwertyui87");

        boolean result = validation.appUser(appUser);

        assertEquals(result, true);
    }


//    @Test
//    void validateEmailWrongAtSignTest() {
//        AppUser appUser = new AppUser( "Fail", "ThisPasswordIsS@f3!");
//
//        boolean result = validation.appUser(appUser);
//
//        assertEquals(!result, true);
//    }


//    @Test
//    void validateEmailWrongPointTest() {
//        AppUser appUser = new AppUser( "Fail", "ThisPasswordIsS@f3!");
//
//        boolean result = validation.appUser(appUser);
//
//        assertEquals(!result, true);
//    }

    @Test
    void validatePasswordHappyFlowTest() {
        AppUser appUser = new AppUser( "Fail", "Fail5@f3!");

        boolean result = validation.appUser(appUser);

        assertEquals(result, true);
    }

    @Test
    void validatePasswordHappyFlowShuffle1Test() {
        AppUser appUser = new AppUser( "Fail", "Fa5@ilfkl");

        boolean result = validation.appUser(appUser);

        assertEquals(result, true);
    }

    @Test
    void validatePasswordHappyFlowShuffle2Test() {
        AppUser appUser = new AppUser( "Fail", "Fai5ilff!");

        boolean result = validation.appUser(appUser);

        assertEquals(result, true);
    }

    @Test
    void validatePasswordHappyFlowShuffle3Test() {
        AppUser appUser = new AppUser( "Fail", "Fai5iLff!");

        boolean result = validation.appUser(appUser);

        assertEquals(result, true);
    }

    @Test
    void validatePasswordWrongCapitalLetterTest() {
        AppUser appUser = new AppUser( "Fail", "fail5@f3!");

        boolean result = validation.appUser(appUser);

        assertEquals(!result, true);
    }


    @Test
    void validatePasswordWrongLowercaseTest() {
        AppUser appUser = new AppUser("Fail", "FAIL5@F3!");

        boolean result = validation.appUser(appUser);

        assertEquals(!result, true);
    }


    @Test
    void validatePasswordWrongNumberTest() {
        AppUser appUser = new AppUser("Fail", "Faill@f!");

        boolean result = validation.appUser(appUser);

        assertEquals(!result, true);
    }


    @Test
    void validatePasswordWrongSymbolTest() {
        AppUser appUser = new AppUser("Fail", "Faill5f3");

        boolean result = validation.appUser(appUser);

        assertEquals(!result, true);
    }

    @Test
    void validatePasswordWrongLenghtTest() {
        AppUser appUser = new AppUser("Fail", "F5@f3!");

        boolean result = validation.appUser(appUser);

        assertEquals(!result, true);
    }

    //    @Test
//    void validateEmailWrongAtSignTest() {
//        AppUser appUser = new AppUser( "Fail", "ThisPasswordIsSf3!", 1L);
//
//        boolean result = validation.appUser(appUser);
//
//        assertEquals(!result, true);
//    }
//
//
//    @Test
//    void validateEmailWrongPointTest() {
//        AppUser appUser = new AppUser( "Fail", "ThisPasswordIsS@f3!", 1L);
//
//        boolean result = validation.appUser(appUser);
//
//        assertEquals(!result, true);
//    }
}
