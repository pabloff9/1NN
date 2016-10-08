import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.util.Collections;
import java.util.List;

/**
 * Created by pablo on 05/10/16.
 */
public class Main {

    public static void main(String[] args) {

        Instances data = null;

        try {
            DataSource source = new DataSource("iris.arff");
            data = source.getDataSet();
        } catch (Exception e) {
            System.err.println("There was a problem while reading the file. Exiting...");
            System.exit(1);
        }

        if (!sabemosQualAtributoEhAClasse(data)) {
            definirClasseComoOUltimoAtributo(data);
        }

        data.stratify(3);

        double distance = distanciaEuclidiana(data.instance(0), data.instance(1));
        System.out.println(distance);

        System.out.println("Classe do primeiro: " + KNNComUmVizinho(data.instance(0), data));


    }

    private static String KNNComUmVizinho(Instance exemplar, Instances conjuntoDeTreinamento) {
        double distanciaMinima = Double.POSITIVE_INFINITY;
        Instance vizinhoMaisProximo = null;
        for (Instance vizinho : conjuntoDeTreinamento) {
            double distanciaParaEsseVizinho = distanciaEuclidiana(exemplar, vizinho);
            if (distanciaParaEsseVizinho < distanciaMinima) {
                distanciaMinima = distanciaParaEsseVizinho;
                vizinhoMaisProximo = vizinho;
            }
        }

        return vizinhoMaisProximo.toString(vizinhoMaisProximo.classAttribute());

    }

    private static void definirClasseComoOUltimoAtributo(Instances data) {
        data.setClassIndex(data.numAttributes() - 1);
    }

    private static boolean sabemosQualAtributoEhAClasse(Instances data) {
        return data.classIndex() != -1;
    }

    private static double distanciaEuclidiana(Instance primeira, Instance segunda) {

        List<Attribute> atributos = Collections.list(primeira.enumerateAttributes());
        double somaDosQuadradosDasDiferencas = 0.0;

        for (Attribute attribute : atributos) {
            somaDosQuadradosDasDiferencas += Math.pow(
                    primeira.value(attribute) - segunda.value(attribute), 2);
        }

        return Math.sqrt(somaDosQuadradosDasDiferencas);
    }

}
