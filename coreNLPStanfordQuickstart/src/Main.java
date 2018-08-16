import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ie.util.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.*;
import edu.stanford.nlp.trees.*;
import java.util.*;


//Keep running out of memory, probably should get
//a better laptop.
//Also starting to wonder how memory consious the writers
//were
public class Main {

    public static String text = "Joe Smith was born in California. " +
            "In 2017, he went to Paris, France in the summer. " +
            "His flight left at 3:00pm on July 10th, 2017. " +
            "After eating some escargot for the first time, Joe said, \"That was delicious!\" " +
            "He sent a postcard to his sister Jane Smith. " +
            "After hearing about Joe's trip, Jane decided she might go to France one day.";

    public static void main(String[] args) {

        Properties props = new Properties();

        props.setProperty("annotators","tokenize,ssplit,pos,lemma,ner,parse,depparse,kbp,quote");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        CoreDocument document = new CoreDocument(text);

        pipeline.annotate(document);

        CoreLabel token = document.tokens().get(10);

        System.out.println("Example token is the following");
        System.out.println(token);

        //get the first sentence
        String sentenceText = document.sentences().get(0).text();

        System.out.println(sentenceText);
        System.out.println();

        CoreSentence sentence = document.sentences().get(1);

        List<String> posTags = sentence.posTags();
        System.out.println("\n Pos tags:");
        System.out.println(posTags);
        System.out.println();

        List<String> nerTags = sentence.nerTags();
        System.out.println("\nner tags for second sentence:");
        System.out.println(nerTags);

        Tree constituencyParse = sentence.constituencyParse();
        System.out.println("]\nConstitutency Parse of le second setnecne");
        System.out.println(constituencyParse);

        SemanticGraph dependencyParse = sentence.dependencyParse();
        System.out.println("\nDendency parse of the second sentence");
        System.out.println(dependencyParse);

        List<RelationTriple> relations = document.sentences().get(4).relations();
        System.out.println("\nKBP relations");
        System.out.println(relations.get(0));


        //Entity mentions
        List<CoreEntityMention> entityMentions = sentence.entityMentions();
        System.out.println("\nEntity mentions");
        System.out.println(entityMentions);


        List<CoreQuote> quotes = document.quotes();
        CoreQuote quote = quotes.get(0);
        System.out.println("\nFirst wquote");
        System.out.println(quote);

        System.out.println("\n oriiginal speaker");
        System.out.println(quote.speaker().get());

        System.out.println("\n Caonoical Speaker of quote");
        System.out.println(quote.canonicalSpeaker().get());


    }
}
