import weka.core.Attribute;
import weka.core.Instance;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by aluno on 06/10/16.
 */
public class Exemplar {

    private Instance exemplarDoWeka;

    public Atributo atributo(int posicao) {
        return new Atributo(exemplarDoWeka.attribute(posicao));
    }

    public List<Atributo> todosOsAtributos() {
        List<Attribute> atributos = Collections.list(exemplarDoWeka.enumerateAttributes());
        return atributos.stream()
                .map(Atributo::new)
                .collect(Collectors.toList());
    }

    public void valor(Atributo atributo) {
        return exemplarDoWeka.value(atribut)
    }
}
