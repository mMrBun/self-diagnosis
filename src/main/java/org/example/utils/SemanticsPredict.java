package org.example.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XUHUIK
 * @create 2023/2/8
 * @describe 语义预测
 */
@Component
public class SemanticsPredict {

    /*询问症状*/
    public static String [] symptom_query = {
            "什么症状", "哪些症状", "症状有哪些", "症状是什么", "什么表征", "哪些表征", "表征是什么",
            "什么现象", "哪些现象", "现象有哪些", "症候", "什么表现", "哪些表现", "表现有哪些",
            "什么行为", "哪些行为", "行为有哪些", "什么状况", "哪些状况", "状况有哪些", "现象是什么",
            "表现是什么", "行为是什么","症状","表现","现象","行为","表征"
    };

    /*询问治疗方法*/
    public static String [] treatment_query = {
            "药", "药品", "用药", "胶囊", "口服液", "炎片", "吃什么药", "用什么药", "怎么办",
            "买什么药", "怎么治疗", "如何医治", "怎么医治", "怎么治", "怎么医", "如何治",
            "医治方式", "疗法", "咋治", "咋办", "咋治", "治疗方法"
    };

    /*询问治疗周期*/
    public static String [] treatment_cycle_query = {
            "周期", "多久", "多长时间", "多少时间", "几天", "几年", "多少天", "多少小时",
            "几个小时", "多少年", "多久能好", "痊愈", "康复","多久能好"
    };

    /*询问治愈率*/
    public static String [] cure_rate_query = {
            "多大概率能治好", "多大几率能治好", "治好希望大么", "几率", "几成", "比例",
            "可能性", "能治", "可治", "可以治", "可以医", "能治好吗", "可以治好吗", "会好吗",
            "能好吗", "治愈吗"
    };

    /*询问检查项目*/
    public static String [] check_query = {
            "检查什么", "检查项目", "哪些检查", "什么检查", "检查哪些", "项目", "检测什么",
            "哪些检测", "检测哪些", "化验什么", "哪些化验", "化验哪些", "哪些体检", "怎么查找",
            "如何查找", "怎么检查", "如何检查", "怎么检测", "如何检测","检查哪里","检查什么项目",
            "项目","检测","检查","查","测"
    };

    /*询问所属科室*/
    public static String [] department_query = {
            "属于什么科", "什么科", "科室", "挂什么", "挂哪个", "哪个科", "哪些科"
    };

    /*询问疾病信息*/
    public static String [] disease_query = {
            "什么病", "啥病", "得了什么", "得了哪种", "怎么回事", "咋回事", "回事",
            "什么情况", "什么问题", "什么毛病", "啥毛病", "哪种病"
    };

    /**
     * 获取输入字符串集合中从左到右任意相邻n项拼接结果
     * @param input
     * @return
     */
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

}
