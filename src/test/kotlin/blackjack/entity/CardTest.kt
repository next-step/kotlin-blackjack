package blackjack.entity

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class CardTest : DescribeSpec({
    describe("Card 객체는") {

        context("생성될 때") {
            it("주어진 Suit와 Rank를 포함해야 한다") {
                val card = Card(Suit.SPADES, Rank.ACE)
                card.suit shouldBe Suit.SPADES
                card.rank shouldBe Rank.ACE
            }
        }

        context("동등성 비교를 할 때") {
            it("같은 Suit와 Rank를 가진 카드 객체는 동일해야 한다") {
                val card1 = Card(Suit.HEARTS, Rank.KING)
                val card2 = Card(Suit.HEARTS, Rank.KING)
                card1 shouldBe card2
            }

            it("다른 Suit를 가진 카드 객체는 서로 달라야 한다") {
                val card1 = Card(Suit.DIAMONDS, Rank.QUEEN)
                val card2 = Card(Suit.CLUBS, Rank.QUEEN)
                card1 shouldNotBe card2
            }

            it("다른 Rank를 가진 카드 객체는 서로 달라야 한다") {
                val card1 = Card(Suit.DIAMONDS, Rank.QUEEN)
                val card2 = Card(Suit.DIAMONDS, Rank.KING)
                card1 shouldNotBe card2
            }
        }
    }
    describe("카드는") {
        context("정보를 물었을 때") {
            forAll(
                row(Card(Suit.HEARTS, Rank.ACE), "A하트"),
                row(Card(Suit.CLUBS, Rank.KING), "K클로버"),
                row(Card(Suit.DIAMONDS, Rank.TWO), "2다이아몬드"),
                row(Card(Suit.SPADES, Rank.QUEEN), "Q스페이드"),
            ) { card, expectedDescription ->
                it("자신의 정보를 설명할 수 있어야 한다: $expectedDescription") {
                    card.describe() shouldBe expectedDescription
                }
            }
        }
    }
})
