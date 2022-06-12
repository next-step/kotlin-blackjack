package blackjack.domain.card

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class DeckTest : DescribeSpec({

    describe("constructor") {
        context("카드 목록이 주어지면") {
            it("덱을 생성한다") {
                val cards = listOf(
                    Card(Suit.DIAMOND, NumberCard(4)),
                    Card(Suit.SPADE, Ace())
                )

                Deck(cards) shouldNotBe null
            }
        }
    }

    describe("draw") {
        it("덱에서 카드를 뽑을 수 있다") {
            val deck = Deck(
                listOf(
                    Card(Suit.SPADE, Ace())
                )
            )

            deck.draw() shouldBe Card(Suit.SPADE, Ace())
        }

        it("덱에서 카드를 뽑으면 카드 목록에서 제외된다") {
            val deck = Deck(
                listOf(
                    Card(Suit.SPADE, Ace())
                )
            )

            deck.draw()

            deck.cards shouldNotContain Card(Suit.SPADE, Ace())
        }
    }
})
