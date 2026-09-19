/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.contentai;import cn.zhuatech.contentai.service.GroundingQualityService;import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class GroundingQualityServiceTests{private final GroundingQualityService s=new GroundingQualityService();/**
                                                                                                          * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                          */
@Test void publishesGroundedContent(){var r=s.evaluate(new GroundingQualityService.Request(10,10,5,0,true,false));assertEquals("PUBLISH",r.status());}/**
                                                                                                                                                                                                                                                                * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                */
@Test void blocksContradictoryHighRiskContent(){var r=s.evaluate(new GroundingQualityService.Request(10,4,1,2,false,true));assertEquals("BLOCK",r.status());}}
