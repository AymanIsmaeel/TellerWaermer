package WaermeGeraet;

public class TellerWaermer {

    private static final int MAX     = 3;

    private static final int LEER    = 1;
    private static final int GEFÜLLT = 2;
    private static final int VOLL    = 3;

    private boolean WarmerIsOn ;
    private int PlatesStatus;
    private int NoOfPlates;
    private String Notify;
    int Max ;

    public TellerWaermer(){
        this.WarmerIsOn = false;
        this.NoOfPlates = 0;
        this.PlatesStatus = 0;
        this.Notify = "";

    }

    public void  Init(){
        if(!this.WarmerIsOn) {
            this.WarmerIsOn = true;
            this.PlatesStatus = LEER;
            this.Notify = "Erfolgreisch eingeschaltet";
            this.Max = MAX;
        }
        else
            this.Notify = "Wärmer ist schon eingeschaltet";
    }

    public boolean pop(){
        if(!this.WarmerIsOn || this.PlatesStatus == LEER){
            this.Notify = (WarmerIsOn ? "Das Wärmegerät ist leer! " : "Das Wärmegerät ist ausgeschaltet! ") + "Sie können keine Teller entnehmen!";
            return false;
        }
        else this.NoOfPlates--;
        if(this.NoOfPlates == 0) this.PlatesStatus = LEER;
        else this.PlatesStatus = GEFÜLLT;
        this.Notify = "Ein Teller entnommen";
        return true;
    }

    public boolean push(){
        if (!this.WarmerIsOn || this.PlatesStatus == VOLL){
            this.Notify = (WarmerIsOn ? "Das Wärmegerät ist voll! " : "Das Wärmegerät ist ausgeschaltet! ") +  "Sie können keine Teller hinzufügen!";
            return false;
        }
        else this.NoOfPlates++;
        if(this.NoOfPlates == this.Max) this.PlatesStatus = VOLL;
        else this.PlatesStatus = GEFÜLLT;
        this.Notify = "Ein Teller hinzugefügt";
        return true;
    }

    public boolean Ansehen(){
        if(!this.WarmerIsOn || this.PlatesStatus == LEER){
            this.Notify = (WarmerIsOn ? "Das Wärmegerät ist leer! " : "Das Wärmegerät ist ausgeschaltet! ") + "Sie können keine Teller ansehen!";
            return false;
        }
        else
            this.Notify = "Ansehen ist möglich";
        return true;
    }

    public boolean Aus(){
        if(!this.WarmerIsOn || this.PlatesStatus != LEER){
            this.Notify = (WarmerIsOn ? "Das Wärmegerät ist noch gefüllt! " : "Das Wärmegerät ist schon ausgeschaltet! " )+ "Sie können den Wärmer nicht ausschalten";
            return false;
        }
        else this.WarmerIsOn = false;
        this.Notify = "Erfolgreisch ausgeschaltet" ;
        return true;
    }

    public String getNotify() {
        return Notify;
    }

    public boolean isWarmerIsOn() {
        return WarmerIsOn;
    }

    public int getPlatesStatus() {
        return PlatesStatus;
    }

    public int getNoOfPlates() {
        return NoOfPlates;
    }

    public void setMax(int max) {
        Max = max;
    }
}




