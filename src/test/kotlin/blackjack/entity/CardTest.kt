package blackjack.entity

import io.kotest.core.spec.style.DescribeSpec
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
})
