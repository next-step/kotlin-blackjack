package blackjack.domain

import blackjack.domain.Denomination.KING
import blackjack.domain.Suit.SPADE
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DealerSpecs : DescribeSpec({

    describe("딜러는") {
        it("카드 덱으로부터 카드를 한 장 뽑을 수 있다.") {
            val deck: Deck = CustomDeck(cards(KING to SPADE))
            val dealer = Dealer(deck = deck)
            dealer.draw() shouldBe Card(KING, SPADE)
        }
        context("카드 덱에 카드가 존재하지 않으면") {
            it("카드 덱으로부터 카드를 뽑을 수 없다.") {
                val deck: Deck = CustomDeck(emptyList())
                val dealer = Dealer(deck = deck)
                shouldThrowExactly<IllegalStateException> {
                    dealer.draw()
                }
            }
        }
    }
})
