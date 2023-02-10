package org.example.utils;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author XUHUIK
 * @create 2023/2/8
 * @describe functional_description
 */
@Component
public class StanfordNLPUtil {
    @Bean
    public void initNlp(){
        String[] chineseArgs = new String[]{"-props","StanfordCoreNLP-chinese.properties"};
        Properties properties = StringUtils.argsToProperties(chineseArgs);
        properties.put("annotators", "tokenize, ssplit, pos, lemma, ner");
        new StanfordCoreNLP(properties);
    }

}
