/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.contentai.ai;
import org.springframework.stereotype.Component; import java.util.Map;
public interface AiProvider { AiResult execute(String prompt,Map<String,String> context); record AiResult(String provider,String answer,Map<String,Object> evidence){} }
@Component class DemoAiProvider implements AiProvider { public AiResult execute(String prompt,Map<String,String> context){return new AiResult("demo-content-provider","演示模式已根据品牌知识生成受控内容草稿，生产环境请替换 AiProvider。",Map.of("sources",4,"confidence",0.93,"promptLength",prompt.length()));} }
