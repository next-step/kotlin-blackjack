package domain

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Suit
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertThrows

class DealerTest : DescribeSpec({
    lateinit var sut: Dealer
    describe("draw card test") {
        context("카드를 뽑는다.") {
            beforeTest {
                val cardList =
                    mutableListOf(
                        Card(Suit.CLUBS, CardNumber.ACE),
                        Card(Suit.DIAMONDS, CardNumber.TWO),
                        Card(Suit.HEARTS, CardNumber.THREE),
                    )
                val blackjackCardDeck = Deck(cardList)
                sut = Dealer(blackjackCardDeck)
            }

            it("MutableList의 첫 인덱스에 있는 카드를 1장 뽑는다.") {
                val actual = sut.draw()
                actual.suit shouldBe Suit.CLUBS
                actual.number shouldBe CardNumber.ACE
            }
        }

        context("덱의 카드를 모두 뽑으면") {
            val cardList =
                mutableListOf(
                    Card(Suit.CLUBS, CardNumber.ACE),
                )
            val blackjackCardDeck = Deck(cardList)
            sut = Dealer(blackjackCardDeck)

            it("throw exception") {
                val blackjackCardListInitSize = cardList.size
                repeat(blackjackCardListInitSize) { sut.draw() }

                val exception =
                    assertThrows<IllegalStateException> {
                        sut.draw()
                    }

                exception.message shouldBe "뽑을 수 있는 카드가 없습니다."
            }
        }
    }
})
