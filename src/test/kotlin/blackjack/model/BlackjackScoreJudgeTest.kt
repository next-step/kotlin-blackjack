package blackjack.model

import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

@DisplayName("블랙잭 점수 심판")
class BlackjackScoreJudgeTest : StringSpec({

    "21점 카드 기준 점수 이하는 에이스 카드 점수 그대로 반환" {
        listOf(
            HandDeck(emptyList()) to 0,
            HandDeck(listOf(SPADE_ACE, DIAMOND_TWO)) to 13,
            HandDeck(listOf(CLOVER_THREE, DIAMOND_TWO)) to 5,
            HandDeck(listOf(SPADE_ACE, CLOVER_THREE)) to 14,
            HandDeck(listOf(HEART_ACE, DIAMOND_TWO, CLOVER_THREE)) to 16,
            HandDeck(listOf(SPADE_ACE, SPADE_KING, SPADE_TEN, CLOVER_THREE)) to 24,
        ).forAll {
            BlackjackScoreJudge.score(it.first) shouldBe it.second
        }
    }

    "21점 카드 기준 점수 이상은 ace 를 1로 계산" {
        listOf(
            HandDeck(listOf(SPADE_ACE, HEART_ACE)) to 12,
            HandDeck(listOf(SPADE_KING, SPADE_TEN, SPADE_ACE)) to 21,
            HandDeck(listOf(HEART_ACE, SPADE_KING, DIAMOND_TWO, CLOVER_THREE)) to 16,
        ).forAll {
            BlackjackScoreJudge.score(it.first) shouldBe it.second
        }
    }
})
