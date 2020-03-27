package io.whitepaper.coder.talks.freemarker;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import static freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS;

public class FreemarkerTemplateTest {

    @org.junit.Test
    public void process() throws Exception {
        String name = "test.ftl";
        String content = "<span>${config.site_desc}</span><br/>\n" +
                "<span>\"${config.site_desc}\"</span>";
        Configuration conf = new Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        conf.setDefaultEncoding("UTF-8");
        StringTemplateLoader tl = new StringTemplateLoader();
        tl.putTemplate(name, content);
        conf.setTemplateLoader(tl);
        Template template = conf.getTemplate(name);

        Map<String, Object> data = new HashMap<>();
        data.put("site_desc", "REPLoop，在脚本编程语言中经常能看到REPL，也就是Read-Evaluate-Print-Loop，意思是读取源代码，评估执行，打印结果的循环。这个过程能够快速的学习实践然后得到结果。非常像一个学习成长的过程：输入信息，消化吸收，输出知识并用于实践，然后持之以恒的坚持。4个环节缺一不可，而且最难做到的大概是持之以恒了。");
        Map<String, Object> model = new HashMap<>();
        model.put("config", data);


        try (Writer writer = new StringWriter()) {
            template.process(model, writer);
            System.out.println(writer.toString());
        }
    }
}