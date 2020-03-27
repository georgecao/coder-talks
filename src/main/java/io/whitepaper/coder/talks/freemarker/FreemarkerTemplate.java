package io.whitepaper.coder.talks.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;

import static freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS;

public class FreemarkerTemplate {

    public void process() throws IOException {
        Configuration conf = new Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        Template template = conf.getTemplate("test.ftl", "UTF-8");
    }
}
