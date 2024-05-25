
import javax.swing.JOptionPane;


public class Main {
    public static void main(String[] args) {
        Parking parking = new Parking("Parking Centro", 31);
        String[] opciones = {"Entrada de coche", "Salida de coche", "Mostrar parking", "Salir"};

        while (true) {
            int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menú",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            
            if (seleccion == -1 || seleccion == 3) {
                break; // Opción "Salir" o ventana cerrada
            }
            
            switch (seleccion) {
                case 0: // Entrada de coche
                    String matriculaEntrada = JOptionPane.showInputDialog("Ingrese la matrícula del coche");
                    if (matriculaEntrada == null) break;
                    
                    String plazaEntradaStr = JOptionPane.showInputDialog("Ingrese el número de plaza");
                    if (plazaEntradaStr == null) break;
                    
                    try {

                        int plazaEntrada = Integer.parseInt(plazaEntradaStr);
                        parking.entrada(matriculaEntrada, plazaEntrada);
                        JOptionPane.showMessageDialog(null, "Coche estacionado en la plaza " + plazaEntrada);
                    } catch (ParkingException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage() + " - Matrícula: " + e.getMatricula(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Número de plaza no válido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 1: // Salida de coche
                    String matriculaSalida = JOptionPane.showInputDialog("Ingrese la matrícula del coche");
                    if (matriculaSalida == null) break;
                    
                    try {
                        int plazaSalida = parking.salida(matriculaSalida);
                        JOptionPane.showMessageDialog(null, "Coche salió de la plaza " + plazaSalida);
                    } catch (ParkingException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage() + " - Matrícula: " + e.getMatricula(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 2: // Mostrar parking
                    JOptionPane.showMessageDialog(null, parking.toString());
                    
                    
                    
                    break;
                default:
                    break;
            }
        }
    }
}

    
