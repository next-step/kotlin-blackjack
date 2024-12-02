package domain

import blackjack.domain.Deck
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DeckTest : DescribeSpec({
    describe("deckTest") {
        context("`Deck`은 4종류의 `Suit`과 13종류의 `CardNumber`를 갖는다.") {
            it("총 52장의 카드가 있다.") {
                val sut = Deck()
                sut.cardList.size shouldBe 52
            }
        }
    }
})
