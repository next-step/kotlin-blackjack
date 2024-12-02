package domain

import blackjack.domain.CardNumber
import blackjack.domain.Deck
import blackjack.domain.Suit
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class DeckTest : DescribeSpec({
    describe("deckTest") {
        context("`Deck`은 4종류의 `Suit`과 13종류의 `CardNumber`를 갖는다.") {
            lateinit var sut: Deck
            beforeTest { sut = Deck() }

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
        }
    }
})
