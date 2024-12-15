package domain

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Suit
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CardTest : DescribeSpec({
    describe("Card init test") {
        it("카드는 Suit과 숫자를 갖는다") {
            val sut = Card(Suit.CLUBS, CardNumber.ACE)
            sut.suit shouldBe Suit.CLUBS
            sut.number shouldBe CardNumber.ACE
        }
    }
})
