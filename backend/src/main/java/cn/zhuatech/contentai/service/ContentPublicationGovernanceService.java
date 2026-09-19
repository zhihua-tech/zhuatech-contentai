/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.contentai.service;
import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.math.*;import java.util.*;
/**
 * 在 AI 内容多渠道发布前执行事实、品牌、版权、个人信息与人工审批门禁。
 *
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class ContentPublicationGovernanceService{
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public Result evaluate(Request r){List<String>blockers=new ArrayList<>(),reviews=new ArrayList<>(),actions=new ArrayList<>();
  BigDecimal coverage=r.factualClaims()==0?BigDecimal.ONE:BigDecimal.valueOf(r.substantiatedClaims()).divide(BigDecimal.valueOf(r.factualClaims()),4,RoundingMode.HALF_UP);
  if(r.prohibitedClaimDetected())blockers.add("内容包含禁止性或无法支持的宣传表述");if(!r.copyrightCleared())blockers.add("素材版权或授权范围未确认");if(r.personalDataIncluded()&&!r.personalDataConsent())blockers.add("个人信息缺少有效授权");if(r.targetsMinors()&&!r.minorSafetyReviewed())blockers.add("面向未成年人内容未完成专项安全复核");
  if(coverage.compareTo(r.minClaimCoverage())<0)reviews.add("事实主张证据覆盖率低于发布阈值");if(!r.brandReviewPassed())reviews.add("品牌语气、商标或禁用词复核未通过");if(!r.humanApproval())reviews.add("内容负责人尚未完成最终审批");if(r.aiGenerated()&&!r.aiDisclosureIncluded())reviews.add("需要披露 AI 生成或辅助编辑信息");
  Decision d=!blockers.isEmpty()?Decision.BLOCKED:!reviews.isEmpty()?Decision.REVIEW:Decision.PUBLISH;
  actions.add(d==Decision.BLOCKED?"停止全部渠道发布并修复法律、安全或隐私问题":d==Decision.REVIEW?"进入内容、法务或品牌人工复核队列":"按渠道版本发布并保存素材、证据和审批快照");
  return new Result(d,coverage,List.copyOf(blockers),List.copyOf(reviews),List.copyOf(actions));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Request(@NotBlank String contentId,@NotEmpty Set<@NotBlank String>channels,@Min(0)int factualClaims,@Min(0)int substantiatedClaims,@DecimalMin("0")@DecimalMax("1")BigDecimal minClaimCoverage,boolean prohibitedClaimDetected,boolean copyrightCleared,boolean personalDataIncluded,boolean personalDataConsent,boolean targetsMinors,boolean minorSafetyReviewed,boolean brandReviewPassed,boolean humanApproval,boolean aiGenerated,boolean aiDisclosureIncluded){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Result(Decision decision,BigDecimal claimEvidenceCoverage,List<String>blockers,List<String>reviewReasons,List<String>actions){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public enum Decision{PUBLISH,REVIEW,BLOCKED}
}
