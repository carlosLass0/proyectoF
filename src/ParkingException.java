
import java.util.logging.Logger;


public class ParkingException extends Exception {
    private String matricula;

    public ParkingException(String matricula) {
        
    }

    public ParkingException(String matricula, String message) {
        super(message);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }
   
    
    
    
    
    

    
    
}
