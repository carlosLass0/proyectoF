
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Parking {
    private ArrayList<String> matriculas;
    private String nombre;
    private HashMap<String, LocalDateTime> tiemposEntrada;

    public Parking(String nombre, int numPlazas) {
        this.nombre = nombre;
        this.matriculas = new ArrayList<>(numPlazas);
        for (int i = 0; i < numPlazas; i++) {
            matriculas.add(null);
        }
        this.tiemposEntrada = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getPlazasTotales() {
        return matriculas.size();
    }

    public int getPlazasOcupadas() {
        int count = 0;
        for (String matricula : matriculas) {
            if (matricula != null) {
                count++;
            }
        }
        return count;
    }

    public int getPlazasLibres() {
        return getPlazasTotales() - getPlazasOcupadas();
    }

    public Duration getTiempoEstacionado(String matricula) throws ParkingException {
        if (!tiemposEntrada.containsKey(matricula)) {
            throw new ParkingException("Matrícula no existente", matricula);
        }
        LocalDateTime tiempoEntrada = tiemposEntrada.get(matricula);
        return Duration.between(tiempoEntrada, LocalDateTime.now());
    }

    public void entrada(String matricula, int plaza) throws ParkingException {
        if (matricula == null || matricula.length() < 4) {
            throw new ParkingException("Matrícula incorrecta", matricula);
        }
        if (plaza < 0 || plaza >= matriculas.size()) {
            throw new ParkingException("Plaza fuera de rango", matricula);
        }
        if (matriculas.get(plaza) != null) {
            throw new ParkingException("Plaza ocupada", matricula);
        }
        if (matriculas.contains(matricula)) {
            throw new ParkingException("Matrícula repetida", matricula);
        }
        matriculas.set(plaza, matricula);
        tiemposEntrada.put(matricula, LocalDateTime.now());
    }

    public int salida(String matricula) throws ParkingException {
        if (!matriculas.contains(matricula)) {
            throw new ParkingException("Matrícula no existente", matricula);
        }
        int plaza = matriculas.indexOf(matricula);
        matriculas.set(plaza, null);
        tiemposEntrada.remove(matricula);
        return plaza;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append("\n----------------\n");
        for (int i = 0; i < matriculas.size(); i++) {
            sb.append("Plaza ").append(i).append(": ");
            if (matriculas.get(i) == null) {
                sb.append("(vacía)");
            } else {
                sb.append(matriculas.get(i));
                try {
                    Duration tiempo = getTiempoEstacionado(matriculas.get(i));
                    long hours = tiempo.toHours();
                    long minutes = tiempo.toMinutes() % 60;
                    long segundos = tiempo.toSeconds() % 60;
                    sb.append(" (").append(hours).append(" horas y ").append(minutes).append(" minutos ").append(segundos).append(" segundos)");
                } catch (ParkingException e) {
                    sb.append(" (error calculando tiempo)");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

   
      
        
    



        
        
    
   
            
    
    

    
    
            

