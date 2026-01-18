package com.cyr.caicode.core;

import com.cyr.caicode.model.enums.CodeGenTypeEnum;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.io.File;
import java.util.List;

@SpringBootTest
class AiCodeGeneratorFacadeTest {

    @Resource
    private AiCodeGeneratorFacade aiCodeGeneratorFacade;

    @Test
    void generateAndSaveCode() {
        File file = aiCodeGeneratorFacade.generateAndSaveCode("生成一个登录页面，总共不超过 20 行代码", CodeGenTypeEnum.MULTI_FILE, 1L);
        Assertions.assertNotNull(file);
    }

    @Test
    void generateAndSaveCodeStream() {
        Flux<String> codeStream = aiCodeGeneratorFacade.generateAndSaveCodeStream("生成一个登录页面，总共不超过 20 行代码", CodeGenTypeEnum.HTML, 1L);
        // 阻塞等待所有数据收集完成
        List<String> result = codeStream.collectList().block();
        // 验证结果
        Assertions.assertNotNull(result);
        // 拼接字符串，得到完整内容
        String completeContent = String.join("", result);
        Assertions.assertNotNull(completeContent);
    }

    @Test
    void generateAndSaveCodeStream_VueProject() {
        // 测试Vue项目生成
        Flux<String> codeStream = aiCodeGeneratorFacade.generateAndSaveCodeStream(
                "创建一个简单的Vue项目，要求不超过200行代码",
                CodeGenTypeEnum.VUE_PROJECT, 
                2L);

        // 阻塞等待所有数据收集完成
        List<String> result = codeStream.collectList().block();

        // 验证结果
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());

        // 拼接字符串，得到完整内容
        String completeContent = String.join("", result);
        Assertions.assertNotNull(completeContent);

        // 验证内容中包含Vue相关的代码片段
        Assertions.assertTrue(completeContent.contains("<template>") || 
                            completeContent.contains("export default") ||
                            completeContent.contains("Vue") ||
                            completeContent.contains("vue"),
                          "生成的内容应该包含Vue相关的代码片段");
    }

    @Test
    void generateAndSaveCodeStream_VueProject_Complex() {
        // 测试更复杂的Vue项目生成
        String userMessage = "创建一个Vue项目，包含以下功能：" +
                "1. 一个登录页面，包含用户名和密码输入框" +
                "2. 一个主页，显示欢迎信息" +
                "3. 使用Vue Router进行路由管理" +
                "4. 使用Pinia进行状态管理";

        Flux<String> codeStream = aiCodeGeneratorFacade.generateAndSaveCodeStream(
                userMessage, 
                CodeGenTypeEnum.VUE_PROJECT, 
                2L);

        // 阻塞等待所有数据收集完成
        List<String> result = codeStream.collectList().block();

        // 验证结果
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());

        // 拼接字符串，得到完整内容
        String completeContent = String.join("", result);
        Assertions.assertNotNull(completeContent);

        // 验证内容中包含Vue相关的代码片段
        Assertions.assertTrue(completeContent.contains("<template>") || 
                            completeContent.contains("export default") ||
                            completeContent.contains("Vue") ||
                            completeContent.contains("vue"),
                          "生成的内容应该包含Vue相关的代码片段");
    }
}
