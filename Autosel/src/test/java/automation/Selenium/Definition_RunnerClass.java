package automation.Selenium;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        // @NR_DS_desktop is default
        tags = {"@Sivabranch2"})

public class Definition_RunnerClass {

    // UAT.meijer for 'DesktopRegression_UAT' branch
    // DEV.meijer for 'Master' branch
    public static final String Environment = "http://uat.meijer.com/";

}
