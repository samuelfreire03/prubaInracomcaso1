package Caso1Folder;

import java.util.Comparator;

public class NumComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        // Se extraen los números de los índices 21 y 23 de cada cadena
        int index1 = s1.indexOf(",");
        int index2 = s2.indexOf(",");
        int num1 = Integer.parseInt(s1.substring(21, index1));
        int num2 = Integer.parseInt(s2.substring(21, index2));
        // Se compara los números extraídos y se retorna el resultado
        return Integer.compare(num1, num2);
    }
}
