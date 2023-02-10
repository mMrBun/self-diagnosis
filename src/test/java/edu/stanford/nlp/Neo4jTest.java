package edu.stanford.nlp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
/**
 * @author XUHUIK
 * @create 2023/2/8
 * @describe functional_description
 */
@SpringBootTest
@Slf4j
public class Neo4jTest {

    public static List<String> nearPermute(List<String> input) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= input.size(); i++) {
            for (int j = 0; j <= input.size() - i; j++) {
                StringBuilder sb = new StringBuilder();
                for (int k = j; k < j + i; k++) {
                    sb.append(input.get(k));
                }
                result.add(sb.toString());
            }
        }
        return result;
    }
    @Test
    public void doNLP(){
        List<String> input = new ArrayList<>();
        input.add("a");
        input.add("b");
        input.add("c");
        input.add("d");
        List<String> permutations = Neo4jTest.nearPermute(input);
        System.out.println(permutations);


    }
}
