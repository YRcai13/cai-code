package com.cyr.caicode.ai;

import com.cyr.caicode.ai.model.HtmlCodeResult;
import com.cyr.caicode.ai.model.MultiFileCodeResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiCodeGeneratorServiceTest {

    @Autowired
    private AiCodeGeneratorService aiCodeGeneratorService;
    @Test
    void generateHtmlCode() {
        HtmlCodeResult codeResult = aiCodeGeneratorService.generateHtmlCode("做一个简单博客，不超过20行");
        Assertions.assertNotNull(codeResult);
    }

    @Test
    void generateMultiFileCode() {
        MultiFileCodeResult codeResult = aiCodeGeneratorService.generateMultiFileCode("做一个简单博客，不超过20行");
        Assertions.assertNotNull(codeResult);
    }
}