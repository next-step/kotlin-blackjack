package blackjack.domain.score

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CardStatusTest : DescribeSpec({

    describe("of") {
        context("카드 점수가 21 이면") {
            it("BLACKJACK 을 반환한다") {
                CardScore.of(Score(21)) shouldBe CardScore.BLACKJACK
            }
        }

        context("카드 점수가 21 초과이면") {
            it("BUST 을 반환한다") {
                CardScore.of(Score(22)) shouldBe CardScore.BUST
            }
        }

        context("카드 점수가 21 미만이면") {
            it("NORMAL 을 반환한다") {
                CardScore.of(Score(20)) shouldBe CardScore.NORMAL
            }
        }
    }
})
