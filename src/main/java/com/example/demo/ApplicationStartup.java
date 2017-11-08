package com.example.demo;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.EnumSet;

/**
 * Created by bozhou on 2017/8/10.
 */
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    public enum Style{height,width};
        public void onApplicationEvent(ContextRefreshedEvent event)
        {
            EnumSet.of(Style.height);
            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");
            try {
                System.out.println(scriptEngine.eval("var a=1;var b=2; a+b;"));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
            System.out.println("=========================="+event.getApplicationContext().getBeansWithAnnotation(MyTestAnn.class));
        }
}