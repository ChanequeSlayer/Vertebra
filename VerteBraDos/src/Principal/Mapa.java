package Principal;

import javax.swing.JOptionPane;

public class Mapa {

    int array[];
    String mapa = "";

    public int[] creacion() {
        int numVertebra = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la cantidad de vertebras que quieres: "));
        int cantidadTPuntos = (numVertebra * 3) + 2;
        array = new int[cantidadTPuntos];

        mapa = "";
        for (int i = 0; i < array.length; i++) {
            if (i < array.length && i == 0) {
                mapa += "[" + array[i] + "]" + ",";
                array[i] = 10;
            } else if (i < array.length) {
                mapa += "[" + array[i] + "]" + ",";
                array[i] = 0;
            } else {
                mapa += "[" + array[i] + "]" + ".";
                array[i] = 11;
            }
        }
        return array;
    }
}
