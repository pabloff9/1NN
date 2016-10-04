import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Spliterator;

/**
 * Created by pablo on 03/10/16.
 */
public class InterpretadorDeBasesCSV<T> implements Iterable<Exemplar>{

    private Scanner fluxoDeLeitura;

    public InterpretadorDeBasesCSV(File baseDeDados) throws FileNotFoundException {
        fluxoDeLeitura = new Scanner(new FileInputStream(baseDeDados));
    }

    @Override
    public Iterator<Exemplar> iterator() {

        return new Iterator<Exemplar>() {

            @Override
            public boolean hasNext() {
                return fluxoDeLeitura.hasNextLine();
            }

            @Override
            public Exemplar next() {

                String proximaLinha = fluxoDeLeitura.nextLine();
                String[] dadosDaLinha = proximaLinha.split(",");

                float[] caracteristicas = new float[dadosDaLinha.length - 1];
                String classe = dadosDaLinha[dadosDaLinha.length - 1];

                return new Exemplar(caracteristicas, classe);
            }
        };

    }

    @Override
    public Spliterator<Exemplar> spliterator() {
        return null;
    }
}
