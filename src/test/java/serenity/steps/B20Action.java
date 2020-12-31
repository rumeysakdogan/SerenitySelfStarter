package serenity.steps;

import net.thucydides.core.annotations.Step;

public class B20Action {

    String actor ;

    // Reports are getting step names from method name
    // unless we did not specify @Step("...") inside step annotation
    @Step("This is where #actor prepare all stuff before taking action")
    public void preparedSomething(){
        System.out.println("preparing cool stuff");
    }

    @Step("#actor Taking some awesome action here")
    public void takeAnAction(){
        System.out.println("taking some action");
    }

    @Step("#actor Eventually expecting a tremendous result")
    public void expectSomeResult(){
        System.out.println("expecting some result");
    }
}
