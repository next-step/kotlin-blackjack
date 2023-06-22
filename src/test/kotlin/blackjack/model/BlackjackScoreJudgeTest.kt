package blackjack.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

@DisplayName("블랙잭 점수 심판")
class BlackjackScoreJudgeTest : StringSpec({
    "블랙잭 기준 점수로 생성" {
        listOf(1, 21, Int.MAX_VALUE).forAll {

            shouldNotThrowAny {
                BlackjackScoreJudge(21)
            }
        }
    }

    "블랙잭 기준 점수는 반드시 0초과" {
        listOf(Int.MIN_VALUE, -1, 0)
            .forAll {
                shouldThrowExactly<IllegalArgumentException> {
                    BlackjackScoreJudge(it)
                }
            }
    }

    "21점 카드 기준 점수 이하는 에이스 카드 점수 그대로 반환" {
        listOf(
            emptyList<TrumpCard>() to 0,
            listOf(SPADE_ACE, DIAMOND_TWO) to 13,
            listOf(CLOVER_THREE, DIAMOND_TWO) to 5,
            listOf(SPADE_ACE, CLOVER_THREE) to 14,
            listOf(HEART_ACE, DIAMOND_TWO, CLOVER_THREE) to 16,
            listOf(SPADE_ACE, SPADE_KING, SPADE_TEN, CLOVER_THREE) to 24,
        ).forAll {
            // given
            val twentyOneScoreLimitJudge = BlackjackScoreJudge(21)
            // when & then
            twentyOneScoreLimitJudge.score(it.first) shouldBe it.second
        }
    }

    "21점 카드 기준 점수 이상은 ace 를 1로 계산" {
        listOf(
            listOf(SPADE_ACE, HEART_ACE) to 12,
            listOf(SPADE_KING, SPADE_TEN, SPADE_ACE) to 21,
            listOf(HEART_ACE, SPADE_KING, DIAMOND_TWO, CLOVER_THREE) to 16,
        ).forAll {
            // given
            val twentyOneScoreLimitJudge = BlackjackScoreJudge(21)
            // when & then
            twentyOneScoreLimitJudge.score(it.first) shouldBe it.second
        }
    }
})
