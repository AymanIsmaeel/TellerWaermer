package WaermeGeraet;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions( features = "classpath:Feature",
                  glue = "WaermeGeraet")
public class TellerWaermerTest {
}
