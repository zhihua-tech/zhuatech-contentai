/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.contentai.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** 在内容发布前检查隐私、禁用词和事实引用完整性。 */
@Service
public class ContentReviewService {
    public ReviewResult review(ReviewRequest request) {
        int uncitedClaims = Math.max(0, request.factualClaims() - request.citedClaims());
        List<String> reasons = new ArrayList<>();
        if (request.containsPersonalData()) reasons.add("内容包含个人信息，需要脱敏");
        if (!request.prohibitedTerms().isEmpty()) reasons.add("命中禁用词: " + String.join(", ", request.prohibitedTerms()));
        if (uncitedClaims > 0) reasons.add("存在 " + uncitedClaims + " 项事实主张缺少引用");
        int riskScore = Math.min(100,
            (request.containsPersonalData() ? 45 : 0)
                + Math.min(30, request.prohibitedTerms().size() * 15)
                + Math.min(25, uncitedClaims * 8));
        String decision = request.containsPersonalData() || !request.prohibitedTerms().isEmpty() ? "BLOCK"
            : uncitedClaims > 0 ? "REVIEW" : "PASS";
        return new ReviewResult(decision, riskScore, uncitedClaims, List.copyOf(reasons),
            "PASS".equals(decision) ? "允许进入发布队列" : "BLOCK".equals(decision) ? "完成脱敏和禁用词整改后重审" : "补充来源引用后提交人工复核");
    }

    public record ReviewRequest(
        @NotBlank(message = "请输入待审核内容") String content,
        @NotBlank(message = "请输入发布渠道") String channel,
        boolean containsPersonalData,
        List<String> prohibitedTerms,
        @PositiveOrZero int factualClaims,
        @PositiveOrZero int citedClaims
    ) {
        public ReviewRequest {
            prohibitedTerms = prohibitedTerms == null ? List.of() : List.copyOf(prohibitedTerms);
        }
    }

    public record ReviewResult(String decision, int riskScore, int uncitedClaims, List<String> reasons, String nextAction) {}
}
