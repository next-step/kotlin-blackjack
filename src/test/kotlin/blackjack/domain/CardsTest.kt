package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardsTest {

    @DisplayName("받은 카드 목록을 가지고 있다.")
    @Test
    fun hasCard() {
        val cards = Cards()
        val cardKD = Card(CardNumber.King, Suit.Diamond)
        val cardQS = Card(CardNumber.Queen, Suit.Spade)

        cards.add(cardKD)
        cards.add(cardQS)

        cards.hasCard(cardKD) shouldBe true
        cards.hasCard(cardQS) shouldBe true
    }

    @DisplayName("다이아몬드 5, 클로버 9의 합계는 총 14점이다")
    @Test
    fun calculate() {
        val cards = Cards()
        val card5D = Card(CardNumber.Five, Suit.Diamond)
        val card9C = Card(CardNumber.Nine, Suit.Clover)

        cards.add(card5D)
        cards.add(card9C)

        cards.totalScore shouldBe 14
    }

    @DisplayName("다이아몬드 에이스, 클로버 9의 합계는 총 20점이다")
    @Test
    fun calculateAce() {
        val cards = Cards()
        val cardAD = Card(CardNumber.Ace, Suit.Diamond)
        val card9C = Card(CardNumber.Nine, Suit.Clover)

        cards.add(cardAD)
        cards.add(card9C)

        cards.totalScore shouldBe 20
    }
}
