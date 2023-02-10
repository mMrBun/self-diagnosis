package org.example.service;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.example.execption.BizException;
import org.example.execption.ResponseCode;
import org.example.model.SentenceStruct;
import org.example.model.node.Department;
import org.example.model.node.Disease;
import org.example.model.node.Drug;
import org.example.model.node.Symptom;
import org.example.pojo.Const;
import org.example.service.relation.DEPARTMENT_IS_Service;
import org.example.service.relation.HAS_DRUG_Service;
import org.example.service.relation.HAS_SYMPTOM_Service;
import org.example.utils.SemanticsPredict;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
/**
 * @author XUHUIK
 * @create 2023/2/8
 * @describe 问诊服务
 */

@Slf4j
@Service
public class DiseaseAnalysisService {

    @Resource
    private HAS_DRUG_Service has_drug_service;

    @Resource
    private DEPARTMENT_IS_Service department_is_service;

    @Resource
    private HAS_SYMPTOM_Service has_symptom_service;

    /**
     * 分析病情描述，根据提问意向返回病情信息
     * @param describe
     * @return
     */
    public List<String> diseaseAnalysis(String describe) {

        List<String> result;

        /*分词，断句，标注词性*/
        List<SentenceStruct> sentenceStruct = sentenceAnalysis(describe);

        /*判断询问意图*/
        String type = analysisType(describe);

        /*根据意图及提问中的病情名称查询病情信息*/
        result = doSearch(sentenceStruct, type);

        if(type == null || CollectionUtils.isEmpty(result)){
            throw new BizException(ResponseCode.QUESTION_DOS_NOT_KNOW_ERROR);
        }

        return result;
    }

    /**
     * 调用StanfordCoreNLP对问题进行分词断句以及词性标注
     * 方便后面对无用词过滤
     * @param describe
     * @return
     */
    public List<SentenceStruct> sentenceAnalysis(String describe){

        List<SentenceStruct> result = new ArrayList<>();

        String[] chineseArgs = new String[]{"-props","StanfordCoreNLP-chinese.properties"};

        Properties properties = StringUtils.argsToProperties(chineseArgs);

        properties.put("annotators", "tokenize, ssplit, pos, lemma, ner");

        StanfordCoreNLP pipLine = new StanfordCoreNLP(properties);

        Annotation annotation = new Annotation(describe);

        pipLine.annotate(annotation);

        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences){

            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)){
                SentenceStruct entity = new SentenceStruct();
                entity.setWord(token.get(CoreAnnotations.TextAnnotation.class));
                entity.setPos(token.get(CoreAnnotations.PartOfSpeechAnnotation.class));
                entity.setNe(token.get(CoreAnnotations.NamedEntityTagAnnotation.class));
                entity.setLemma(token.get(CoreAnnotations.LemmaAnnotation.class));
                result.add(entity);
            }

        }

        return result;
    }

    /**
     * 询问题图判断，如果没有匹配到意图默认为询问疾病
     * @param describe
     * @return
     */
    public String analysisType(String describe) {

        Map<String, String[]> queryMap = new HashMap<>();

        queryMap.put(Const.Disease+";checklist", SemanticsPredict.check_query);

        queryMap.put(Const.Disease+";rate", SemanticsPredict.cure_rate_query);

        queryMap.put(Const.Department, SemanticsPredict.department_query);

        queryMap.put(Const.Disease, SemanticsPredict.disease_query);

        queryMap.put(Const.Symptom, SemanticsPredict.symptom_query);

        queryMap.put(Const.Disease+";period", SemanticsPredict.treatment_cycle_query);

        queryMap.put(Const.Drug, SemanticsPredict.treatment_query);

        for (Map.Entry<String, String[]> entry : queryMap.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();

            if (key == null) {
                continue;
            }

            for (String s : value) {
                if (describe.contains(s)) {
                    return key;
                }
            }
        }

        return Const.Disease;
    }

    /**
     * 对过滤后的词进行类型匹配
     * 匹配询问词在库中是什么类型的数据
     * @param entityName
     * @return
     */
    public String entityReg(String entityName) {

        final String ALIAS_VOCAB = "/data/alias_vocab.txt";

        final String COMPLICATIONS_VOCAB = "/data/complications_vocab.txt";

        final String DISEASE_VOCAB = "/data/disease_vocab.txt";

        final String SYMPTOM_VOCAB = "/data/symptom_vocab.txt";


        ClassPathResource aliasPath = new ClassPathResource(ALIAS_VOCAB);

        ClassPathResource complicationsPath = new ClassPathResource(COMPLICATIONS_VOCAB);

        ClassPathResource diseasePath = new ClassPathResource(DISEASE_VOCAB);

        ClassPathResource symptomPath = new ClassPathResource(SYMPTOM_VOCAB);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(diseasePath.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(entityName)) {
                    return Const.Disease;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(aliasPath.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(entityName)) {
                    return Const.Alias;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(symptomPath.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(entityName)) {
                    return Const.Symptom;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(complicationsPath.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(entityName)) {
                    return Const.Complication;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    /**
     * 根据所给询问意图及分词查询病情数据
     * @param sentenceStruct
     * @param type
     * @return
     */
    public List<String> doSearch(List<SentenceStruct> sentenceStruct,String type){

        if(type == null){
            return null;
        }

        sentenceStruct.removeIf(struct -> struct.getPos().equals(Const.AD) || struct.getPos().equals(Const.NT) || struct.getPos().equals(Const.PN) || struct.getPos().equals(Const.PU));

        String entityType;

        List<String> result = new ArrayList<>();

        List<String> collect = sentenceStruct.stream().map(SentenceStruct::getWord).collect(Collectors.toList());

        List<String> question = SemanticsPredict.nearPermute(collect);

        for(int i = question.size()-1; i>=0; i--){
            /*判断询问的对象是什么类型*/
            entityType = entityReg(question.get(i));

            if(entityType!=null){

                List<Disease> entity;

                switch(entityType){
                    case Const.Disease:
                        entity = has_drug_service.getDiseaseByName(question.get(i));
                        addEntity(type, result, question.get(i), entity);
                        break;
                    case Const.Complication:
                        entity = has_drug_service.getDiseaseByComplication(question.get(i));
                        addEntity(type, result, question.get(i), entity);
                        break;
                    case Const.Alias:
                        entity = has_drug_service.getDiseaseByAlias(question.get(i));
                        addEntity(type, result, question.get(i), entity);
                        break;
                    case Const.Symptom:
                        entity = has_drug_service.getDiseaseBySymptom(question.get(i));
                        addEntity(type, result, question.get(i), entity);
                        break;
                }

            }

        }

        return result;
    }

    private void addEntity(String type, List<String> result, String word, List<Disease> entity) {
        if(!CollectionUtils.isEmpty(entity)){
            for(Disease disease : entity){
                if(type.contains(Const.Disease)&&type.contains(";")){
                    /*查询疾病的属性*/
                    List<String> strings = StringUtils.split(type, ";");
                    switch (strings.get(1)) {
                        case "checklist":
                            result.add("疾病 0 的检查部位如下：1 "
                                    .replace("0", disease.getName())
                                    .replace("1", disease.getChecklist()));
                            return;
                        case "rate":
                            result.add("疾病 0 的治愈率为：1 "
                                    .replace("0", disease.getName())
                                    .replace("1", disease.getRate()));
                            return;
                        case "period":
                            result.add("疾病 0 的治疗周期为：1 "
                                    .replace("0", disease.getName())
                                    .replace("1", disease.getPeriod()));
                            return;
                    }
                }else if(type.contains(Const.Disease)&&!type.contains(";")){
                    /*单纯查疾病*/
                    result.add("疾病 0 的描述信息如下：发病人群：1 医保：2 传染性：3 检查项目：4 治愈周期：5 治愈率：6 费用：7"
                            .replace("0",disease.getName())
                            .replace("1",disease.getAge())
                            .replace("2",disease.getInsurance())
                            .replace("3",disease.getInfection())
                            .replace("4",disease.getChecklist())
                            .replace("5",disease.getPeriod())
                            .replace("6",disease.getRate())
                            .replace("7",disease.getMoney()));
                    return;
                }else if(type.equals(Const.Department)){
                    List<Department> departments = department_is_service.getDepartments(word);
                    for(Department department :departments){
                        result.add("疾病 0 的所属科室为：1"
                                .replace("0",disease.getName())
                                .replace("1",department.getName()));
                    }
                    return;
                }else if(type.equals(Const.Drug)){
                    List<Drug> drugs = has_drug_service.getDrugs(word);
                    for(Drug drug :drugs){
                        result.add("疾病 0 的治疗药品为：1"
                                .replace("0",disease.getName())
                                .replace("1",drug.getName()));
                    }
                    return;
                }else if(type.equals(Const.Symptom)){
                    List<Symptom> symptoms = has_symptom_service.getSymptoms(word);
                    for(Symptom symptom :symptoms){
                        result.add("疾病 0 的症状为：1"
                                .replace("0",disease.getName())
                                .replace("1",symptom.getName()));
                    }
                    return;
                }
            }
        }
    }


}
