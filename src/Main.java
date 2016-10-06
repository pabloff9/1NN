import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Instances exemplares = null;

        try {
            DataSource source = new DataSource("iris.arff");
            exemplares = source.getDataSet();
        } catch (Exception e) {
            System.err.println("There was a problem while reading the file. Exiting...");
            System.exit(1);
        }

        if (!sabemosQualAtributoEhAClasse(exemplares)) {
            definirClasseComoOUltimoAtributo(exemplares);
        }


        Random random = new Random(34);
        Instances copiaDosExemplares = new Instances(exemplares);

        copiaDosExemplares.randomize(random);

        int folds = 3;

        copiaDosExemplares.stratify(folds);

        Instances conjuntoDeTreinamento = copiaDosExemplares.trainCV(folds, 0);
        Instances conjuntoDeTeste = copiaDosExemplares.testCV(folds, 0);

        // calculando a acurácia do 1NN

        int acertos = 0;
        for (int i = 0; i < conjuntoDeTeste.size(); i++) {
            Instance exemplar = conjuntoDeTeste.instance(i);
            String classePrevista = KNNComUmVizinho(exemplar, conjuntoDeTreinamento);
            String classeReal = exemplar.toString(exemplar.classAttribute());

            if (classePrevista.equals(classeReal)) {
                acertos++;
            } else {
                System.out.println("Errei o exemplar " + exemplar.toString());
            }

        }

        System.out.println("Acurácia: " + acertos / (double) conjuntoDeTeste.size());

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
