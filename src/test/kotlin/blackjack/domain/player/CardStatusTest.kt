package blackjack.domain.player

import blackjack.domain.Score
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CardStatusTest : DescribeSpec({

    describe("of") {
        context("카드 점수가 21 이면") {
            it("BLACKJACK 을 반환한다") {
                CardStatus.of(Score(21)) shouldBe CardStatus.BLACKJACK
            }
        }

        context("카드 점수가 21 초과이면") {
            it("BUST 을 반환한다") {
                CardStatus.of(Score(22)) shouldBe CardStatus.BUST
            }
        }

        context("카드 점수가 21 미만이면") {
            it("NORMAL 을 반환한다") {
                CardStatus.of(Score(20)) shouldBe CardStatus.NORMAL
            }
        }
    }
})
