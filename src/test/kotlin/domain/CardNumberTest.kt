package domain

import blackjack.domain.CardNumber
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CardNumberTest : DescribeSpec({
    lateinit var sut: CardNumber

    describe("Ace Test") {
        context("Ace는 1 또는 11로 계산할 수 있다.") {
            it("11을 더해서 카드의 합이 21이 넘는 경우 1로 계산한다.") {
                sut = CardNumber.ACE
                sut.appendScore(11) shouldBe 1
            }

            it("11을 더해서 카드의 합이 21이 넘지 않는 경우 경우 11로 계산한다.") {
                sut = CardNumber.ACE
                sut.appendScore(10) shouldBe 11
            }
        }
    }
})
