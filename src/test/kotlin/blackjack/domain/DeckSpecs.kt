package blackjack.domain

import blackjack.domain.Denomination.KING
import blackjack.domain.Suit.SPADE
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.types.shouldBeSameInstanceAs

class DeckSpecs : DescribeSpec({

    describe("카드 덱은") {
        it("카드 한 장을 뽑을 수 있다") {
            val deck: Deck = CustomDeck(
                listOf(
                    Card.from(KING, SPADE),
                )
            )
            deck.draw() shouldBeSameInstanceAs Card.from(KING, SPADE)
        }
        context("덱에 카드가 남아있지 않은 경우") {
            it("카드를 뽑을 수 없다") {
                val deck: Deck = CustomDeck(
                    emptyList()
                )
                shouldThrowExactly<IllegalStateException> {
                    deck.draw()
                }
            }
        }
    }
})
