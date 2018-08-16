import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ie.util.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.*;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.*;

import java.util.*;


//My first really simple program using
//Core NLP
//Scherban, Christopher
// Georgia Tech 2020

public class MyFirstProgram {



    public static void main(String[] args) {

        Properties props = new Properties();

        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // read some text in the text variable
        String text = "You are a man, I am a woman.";

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(text);

        // run all Annotators on this text
        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for(CoreMap sentence : sentences){
            for(CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)){

                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                System.out.println("\nWord: ");
                System.out.println(word);

                System.out.println("\nNER: ");
                System.out.println(ne);

                System.out.println("\nParts of Speach: ");
                System.out.println(pos);
            }
        }

    }

}
