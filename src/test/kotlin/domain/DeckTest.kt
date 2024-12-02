package domain

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.Deck
import blackjack.domain.Suit
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.assertThrows

class DeckTest : DescribeSpec({
    lateinit var sut: Deck
    beforeTest { sut = Deck() }

    describe("deckTest init test") {
        context("`Deck`은 4종류의 `Suit`과 13종류의 `CardNumber`를 갖는다.") {
            it("총 52장의 카드가 있다.") {
                sut.cardList.size shouldBe 52
            }

            it("Suit의 종류는 `clubs, diamonds, hearts, spades`이다") {
                val suitList = sut.cardList.map { card -> card.suit }.toList()
                suitList shouldContain Suit.CLUBS
                suitList shouldContain Suit.DIAMONDS
                suitList shouldContain Suit.HEARTS
                suitList shouldContain Suit.SPADES
            }

            it("카드 각 숫자는 4개씩 존재한다") {
                val actual: Map<CardNumber, Int> = sut.cardList.groupingBy { it.number }.eachCount()
                actual.values.forEach {
                    it shouldBe 4
                }
            }

            // fixme: 낮은 확률로 실패할 수 있는 테스트 -> 어떻게 개선할 수 있을까요?
            it("카드는 셔플되어야 한다") {
                val originalOrder = sut.cardList.sortedBy { it.number.ordinal }
                val shuffledOrder = sut.cardList
                shuffledOrder shouldNotBe originalOrder
            }
        }
    }

    describe("hit test") {
        context("카드를 뽑는다.") {
            it("MutableList의 첫 인덱스에 있는 카드를 1장 뽑는다.") {
                val beforeHit = sut.cardList.size
                sut.draw()
                val afterHit = sut.cardList.size

                afterHit shouldBe beforeHit - 1
            }
        }

        context("덱의 size가 1보다 작으면") {
            it("throw exception") {
                sut =
                    Deck(
                        mutableListOf(
                            Card(Suit.CLUBS, CardNumber.ACE),
                        ),
                    )

                sut.draw()

                assertThrows<IllegalStateException> {
                    sut.draw()
                }
            }
        }
    }
})
