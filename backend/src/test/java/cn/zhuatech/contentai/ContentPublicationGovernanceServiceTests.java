/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.contentai;import cn.zhuatech.contentai.service.ContentPublicationGovernanceService;import org.junit.jupiter.api.Test;import java.math.BigDecimal;import java.util.Set;import static org.assertj.core.api.Assertions.assertThat;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class ContentPublicationGovernanceServiceTests{private final ContentPublicationGovernanceService s=new ContentPublicationGovernanceService();
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void publishesGovernedContent(){var r=s.evaluate(req(10,10,false,true,true,true,true));assertThat(r.decision()).isEqualTo(ContentPublicationGovernanceService.Decision.PUBLISH);}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void reviewsWeakEvidenceAndMissingApproval(){var r=s.evaluate(req(10,5,false,true,false,false,false));assertThat(r.decision()).isEqualTo(ContentPublicationGovernanceService.Decision.REVIEW);assertThat(r.reviewReasons()).hasSize(4);}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void blocksIllegalOrUnlicensedContent(){var r=s.evaluate(req(1,1,true,false,true,true,true));assertThat(r.decision()).isEqualTo(ContentPublicationGovernanceService.Decision.BLOCKED);assertThat(r.blockers()).hasSize(2);}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 private ContentPublicationGovernanceService.Request req(int claims,int supported,boolean prohibited,boolean copyright,boolean brand,boolean approved,boolean disclosure){return new ContentPublicationGovernanceService.Request("C-1",Set.of("web"),claims,supported,new BigDecimal("0.8"),prohibited,copyright,false,true,false,true,brand,approved,true,disclosure);}}
