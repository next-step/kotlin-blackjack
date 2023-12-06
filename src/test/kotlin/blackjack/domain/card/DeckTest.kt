package blackjack.domain.card

import blackjack.mock.deck
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DeckTest : DescribeSpec({
    describe("Deck.from()") {
        context("카드 리스트로") {
            val cards = listOf(Card(Suit.CLUB, Rank.SEVEN), Card(Suit.DIAMOND, Rank.FOUR))

            it("덱을 생성한다") {
                val result = Deck.from(cards)

                result.cards shouldBe cards
            }
        }
    }
    describe("draw()") {
        context("덱에서 카드를 한 장을 빼면") {
            val cards = listOf(Card(Suit.HEART, Rank.FOUR), Card(Suit.CLUB, Rank.SEVEN))
            val deck = deck(cards)

            val result = deck.draw()

            it("맨 뒤의 카드 가져온다") {
                result shouldBe Card(Suit.CLUB, Rank.SEVEN)
            }

            it("덱에서는 해당 카드 제거한다") {
                deck.cards shouldBe listOf(Card(Suit.HEART, Rank.FOUR))
            }
        }

        context("덱에 카드가 없으면") {
            val deck = Deck(ArrayDeque())

            it("카드 한장 빼기에 실패한다") {
                shouldThrowExactly<IllegalArgumentException> {
                    deck.draw()
                }
            }
        }
    }
})
