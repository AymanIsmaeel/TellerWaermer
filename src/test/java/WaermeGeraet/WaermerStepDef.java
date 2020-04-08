package WaermeGeraet;

import cucumber.api.java.en.*;
import static org.assertj.core.api.Assertions.assertThat;

public class WaermerStepDef {
    private static final int MAX     = 3;

    private static final int LEER    = 1;
    private static final int GEFÜLLT = 2;
    private static final int VOLL    = 3;
    TellerWaermer warmer;


//////////// Scenario: Wärmegerät einschalten ////////////////////////////////////////
    @Given("das Wärmegerät ist ausgeschaltet")
    public void das_Wärmegerät_ist_ausgeschaltet() {
        warmer = new TellerWaermer();
    }

    @When("ich das Wärmegerät einschalte")
    public void ich_das_Wärmegerät_einschalte() {
        warmer.Init();
    }

    @Then("ist das Wärmegerät betriebsbereit")
    public void ist_das_Wärmegerät_betriebsbereit() {
        assertThat(warmer.isWarmerIsOn());
    }

    @Then("der Tellerstapel ist leer")
    public void der_Tellerstapel_ist_leer() {
        assertThat(warmer.getNoOfPlates()).isEqualTo(0);
        assertThat(warmer.getPlatesStatus()).isEqualTo(LEER);

    }

////////// Scenario: Leeres Wärmegerät ausschalten //////////////////////////////////////////////////
    @Given("das Wärmegerät ist eingeschaltet")
    public void das_Wärmegerät_ist_eingeschaltet() {
        warmer = new TellerWaermer();
        warmer.Init();
    }

    @Given("das Wärmegerät ist leer")
    public void das_Wärmegerät_ist_leer() {
        warmer.Init();
    }

    @When("ich das Wärmegerät ausschalte")
    public void ich_das_Wärmegerät_ausschalte() {
        warmer.Aus();
    }

    @Then("ist das Wärmegerät ausser Betrieb")
    public void ist_das_Wärmegerät_ausser_Betrieb() {
        assertThat(warmer.isWarmerIsOn() == false);
    }

    @Then("es können keine Teller hinzugefügt werden")
    public void es_können_keine_Teller_hinzugefügt_werden() {
        assertThat(warmer.push() == false);
    }

////////// Scenario: Gefülltes Wärmegerät ausschalten ////////////////////////////////////////////////////////////
    @Given("das Wärmegerät ist gefüllt")
    public void das_Wärmegerät_ist_gefüllt() {
        warmer = new TellerWaermer();
        warmer.Init();
        warmer.push();
    }

    //  @When("ich das Wärmegerät ausschalte") // schon definiert

    @Then("erhalte ich einen Hinweis, dass das Wärmegerät noch gefüllt ist")
    public void erhalte_ich_einen_Hinweis_dass_das_Wärmegerät_noch_gefüllt_ist() {
        assertThat(warmer.getNotify()).isEqualTo("Das Wärmegerät ist noch gefüllt! Sie können den Wärmer nicht ausschalten");
    }

    @Then("das Wärmegerät bleibt in Betrieb")
    public void das_Wärmegerät_bleibt_in_Betrieb() {
        assertThat(warmer.isWarmerIsOn());
    }

////////////// Scenario: Volles Wärmegerät ausschalten //////////////////////////////////////////////////
    // @Given("das Wärmegerät ist eingeschaltet")

    @Given("das Wärmegerät ist voll")
    public void das_Wärmegerät_ist_voll() {
        while (warmer.getPlatesStatus() != VOLL){
            warmer.push();
        }
    }

    //  @When("ich das Wärmegerät ausschalte") // schon definiert
    // @Then("erhalte ich einen Hinweis, dass das Wärmegerät noch gefüllt ist")
    // @Then("das Wärmegerät bleibt in Betrieb")

////////////// Scenario Outline: Teller hinzufügen/entnehmen //////////////////////////////////////////////////////////
    // @Given("das Wärmegerät ist eingeschaltet")

    @Given("die maximale Kapazität des Tellerstapels ist {int}")
    public void die_maximale_Kapazität_des_Tellerstapels_ist(Integer max) {
        warmer.setMax(max);
    }

    @Given("im Wärmegerät sind {int} Teller")
    public void im_Wärmegerät_sind_Teller(Integer NoOfPlates) {
        while(warmer.getNoOfPlates() < NoOfPlates){
            warmer.push();
        }
    }

    @When("^ich einen Teller (.*)$")
    public void ich_einen_Teller_hinzufüge(String maßnahme) {
        switch (maßnahme) {
            case "Hinzufügen" : warmer.push(); break;
            case "Entnehmen"  : warmer.pop();  break;
            case "Ansehen"    : warmer.Ansehen(); break;
        }
    }

    @Then("sind {int} Teller im Wärmegerät")
    public void sind_Teller_im_Wärmegerät(Integer NoOfPlatesNew) {
        assertThat(warmer.getNoOfPlates()).isEqualTo(NoOfPlatesNew);
    }

    @Then("^eine (.*) informiert mich ob das (?:Hinzufügen|Entnehmen|Ansehen) erfolgreich war$")
    public void eine_Hinzufügen_OK_informiert_mich_ob_das_hinzufügen_erfolgreich_war(String Meldung) {
        assertThat(warmer.getNotify()).isEqualTo(Meldung);
    }

}


