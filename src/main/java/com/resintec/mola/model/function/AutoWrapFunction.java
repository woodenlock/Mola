package com.resintec.mola.model.function;

import java.util.List;

import com.resintec.mola.util.CommonUtils;

import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * a customize freemarker function for splitting Strings of the mybatis mapper file
 * @author woodenlock
 */
public class AutoWrapFunction implements TemplateMethodModelEx {
	
    private static final String BREAK_SPLITER = ",";
    
    private static final String WRAP_SIGNER = "\n";
    
    private static final Integer MIN_PARAMS_SIZE = 2;
    
    private static final Integer MAX_PARAMS_SIZE = 3;
    
    @SuppressWarnings("rawtypes")
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if(null == arguments || (arguments.size() != MIN_PARAMS_SIZE && arguments.size() != MAX_PARAMS_SIZE)){
            throw new IllegalArgumentException("Failed to execute customize freemarker function[AutoWrapFunction]"
                + " due to unmatched params:" + arguments);
        }
        String content = null;
        int count = 0;
        int spaceCount = 5;
        try {
            content = ((SimpleScalar)(arguments.get(0))).getAsString();
            count = ((SimpleNumber)(arguments.get(1))).getAsNumber().intValue();
            if(arguments.size() == MAX_PARAMS_SIZE){
                spaceCount = ((SimpleNumber)(arguments.get(2))).getAsNumber().intValue();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to execute customize freemarker function[AutoWrapFunction]"
                + " due to illegal params:" + arguments);
        }
        if(CommonUtils.isBlank(content) || count == 0){
            return null;
        }
        if(content.length() <= count){
            return content;
        }
        StringBuffer spaces = new StringBuffer();
        for (int i = 0; i < spaceCount; i++) {
            spaces.append(" ");
        }
        String space = spaces.toString();
        StringBuffer result = new StringBuffer();
        String tmp = null;
        while(content.length() > count){
            tmp = content.substring(0, count + 1);
            int indedx = tmp.lastIndexOf(BREAK_SPLITER);
            result.append(content.substring(0, indedx + 1) + WRAP_SIGNER + space);
            content = content.substring(indedx + 1);
        }
        result.append(content);
        
        return result.toString();
    }
}
