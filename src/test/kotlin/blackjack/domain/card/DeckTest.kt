package blackjack.domain.card

import blackjack.mock.deck
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DeckTest : DescribeSpec({
    describe("카드 리스트로 덱 생성") {
        context("카드 리스트로") {
            val cards = listOf(Card(Suit.CLUB, Rank.SEVEN), Card(Suit.DIAMOND, Rank.FOUR))

            it("덱 생성") {
                val result = Deck.from(cards)

                result.cards shouldBe cards
            }
        }
    }
    describe("덱에서 카드 제거") {
        context("덱에서 카드를 한 장 빼면") {
            val cards = listOf(Card(Suit.HEART, Rank.FOUR), Card(Suit.CLUB, Rank.SEVEN))
            val deck = deck(cards)

            val result = deck.draw()

            it("맨 뒤의 카드 가져옴") {
                result shouldBe Card(Suit.CLUB, Rank.SEVEN)
            }

            it("덱에서는 해당 카드 제거") {
                deck.cards shouldBe listOf(Card(Suit.HEART, Rank.FOUR))
            }
        }

        context("덱에 카드가 없으면") {
            val deck = Deck(ArrayDeque())

            it("카드 가져오기 실패") {
                shouldThrowExactly<IllegalArgumentException> {
                    deck.draw()
                }
            }
        }
    }
})
