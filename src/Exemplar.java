import java.util.ArrayList;

/**
 * Created by pablo on 03/10/16.
 */
public class Exemplar {

    private float[] vetorDeCaracteristicas;
    private String classe;

    public Exemplar(float[] vetorDeCaracteristicas, String classe) {
        this.vetorDeCaracteristicas = vetorDeCaracteristicas;
        this.classe = classe;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("");
        for (float caracteristica: vetorDeCaracteristicas) {
            builder.append(caracteristica)
                    .append(",");
        }
        builder.append(classe);
        return builder.toString();
    }
}
