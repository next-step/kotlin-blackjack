package blackjack.domain

import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardsTest {

    @DisplayName("추가한 카드를 가지고 있다")
    @Test
    fun addCard() {
        val cards = Cards(listOf(Card(CardNumber.King, Suit.Diamond)))

        val cardQS = Card(CardNumber.Queen, Suit.Spade)
        cards.add(cardQS)

        val expected = listOf(Card(CardNumber.King, Suit.Diamond), Card(CardNumber.Queen, Suit.Spade))

        cards.getCardList() shouldContainInOrder expected
    }

    @DisplayName("받은 카드 목록을 가지고 있다.")
    @Test
    fun hasCard() {
        val cardKD = Card(CardNumber.King, Suit.Diamond)
        val cardQS = Card(CardNumber.Queen, Suit.Spade)

        val cards = Cards(listOf(cardKD, cardQS))

        cards.getCardList() shouldContainInOrder listOf(cardKD, cardQS)
    }

    @DisplayName("다이아몬드 5, 클로버 9의 합계는 총 14점이다")
    @Test
    fun calculate() {
        val cards = Cards(listOf(Card(CardNumber.Five, Suit.Diamond), Card(CardNumber.Nine, Suit.Clover)))

        cards.totalScore shouldBe 14
    }

    @DisplayName("다이아몬드 에이스, 클로버 9의 합계는 총 20점이다")
    @Test
    fun calculateAce() {
        val cards = Cards(listOf(Card(CardNumber.Ace, Suit.Diamond), Card(CardNumber.Nine, Suit.Clover)))

        cards.totalScore shouldBe 20
    }
}
