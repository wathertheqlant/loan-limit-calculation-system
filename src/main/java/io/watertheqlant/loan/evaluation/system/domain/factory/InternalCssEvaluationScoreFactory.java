package io.watertheqlant.loan.evaluation.system.domain.factory;

import io.watertheqlant.loan.evaluation.system.domain.entity.InternalCssEvaluationScore;
import io.watertheqlant.loan.evaluation.system.domain.entity.InternalCssGradePolicy;
import io.watertheqlant.loan.evaluation.system.domain.vo.InternalEvaluationPolicyResult;
import io.watertheqlant.loan.evaluation.system.domain.entity.InternalCssEvaluationScoreItem;

import java.time.LocalDateTime;
import java.util.List;

public class InternalCssEvaluationScoreFactory {

  public static InternalCssEvaluationScore create(
    LocalDateTime requestedAt,
    Long customerId,
    Long scbCreditInfoId,
    InternalCssGradePolicy internalCssGradePolicy,
    List<InternalEvaluationPolicyResult> internalEvaluationPolicyResults
  ) {
    return InternalCssEvaluationScore.builder()
      .customerId(customerId)
      .internalCssGradePolicyId(internalCssGradePolicy.getId())
      .cssGrade(internalCssGradePolicy.getCssGrade())
      .scbCreditInfoId(scbCreditInfoId)
      .expiredAt(requestedAt)
      .evaluatedAt(requestedAt)
      .items(internalEvaluationPolicyResults.stream()
        .map(result -> createItem(customerId, result))
        .toList()
      )
      .build();
  }

  private static InternalCssEvaluationScoreItem createItem(Long customerId, InternalEvaluationPolicyResult result) {
    return InternalCssEvaluationScoreItem.builder()
      .customerId(customerId)
      .internalEvaluationPolicyId(result.getInternalEvaluationPolicyId())
      .evaluationResultType(result.getEvaluationResultType())
      .evaluatedValue(result.getEvaluatedValue())
      .score(result.getScore())
      .build();

  }

}
