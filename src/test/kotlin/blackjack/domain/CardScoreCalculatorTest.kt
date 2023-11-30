package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CardScoreCalculatorTest {
    @Test
    fun `Blackjack 점수와 일치했을때 isBlackjack 메서드는 true를 반환한다`() {
        // given
        val cards = Cards(
            listOf(
                Card(Suit.SPADES, Denomination.ACE),
                Card(Suit.SPADES, Denomination.KING)
            )
        )

        // when
        val result = CardScoreCalculator.isBlackjack(cards)

        // then
        assertEquals(true, result)
    }

    @Test
    fun `Blackjack 점수와 일치하지 않을 때 isBlackjack 메서드는 false를 반환한다`() {
        // given
        val cards = Cards(
            listOf(
                Card(Suit.SPADES, Denomination.ACE)
            )
        )

        // when
        val result = CardScoreCalculator.isBlackjack(cards)

        // then
        assertEquals(false, result)
    }

    @Test
    fun `Blackjack 점수를 초과했을때 isBust 메서드는 true를 반환한다`() {
        // given
        val cards = Cards(
            listOf(
                Card(Suit.SPADES, Denomination.KING),
                Card(Suit.SPADES, Denomination.KING),
                Card(Suit.SPADES, Denomination.KING)
            )
        )

        // when
        val result = CardScoreCalculator.isBust(cards)

        // then
        assertEquals(true, result)
    }

    @Test
    fun `Blackjack 점수를 초과하지 않았을 때 isBust 메서드는 false를 반환한다`() {
        // given
        val cards = Cards(
            listOf(
                Card(Suit.SPADES, Denomination.KING)
            )
        )

        // when
        val result = CardScoreCalculator.isBust(cards)

        // then
        assertEquals(false, result)
    }

    @Test
    fun `cards 점수가 특정 기준치를 넘었다면 isOverScore 메서드는 true를 반환한다`() {
        // given
        val cards = Cards(
            listOf(
                Card(Suit.SPADES, Denomination.KING)
            )
        )

        // when
        val result = CardScoreCalculator.isOverScore(cards, 5)

        // then
        assertEquals(true, result)
    }

    @Test
    fun `cards 점수가 특정 기준치를 넘지 않았다면 isOverScore 메서드는 false를 반환한다`() {
        // given
        val cards = Cards(
            listOf(
                Card(Suit.SPADES, Denomination.KING)
            )
        )

        // when
        val result = CardScoreCalculator.isOverScore(cards, 10)

        // then
        assertEquals(false, result)
    }

    @Test
    fun `두 cards를 비교할 때 첫번째 cards 점수가 blackjack에 더 가깝다면 isCloseToBlackjack 메서드는 true를 반환한다`() {
        // given
        val cards1 = Cards(
            listOf(
                Card(Suit.SPADES, Denomination.KING),
                Card(Suit.SPADES, Denomination.KING)
            )
        )
        val cards2 = Cards(
            listOf(
                Card(Suit.SPADES, Denomination.TWO)
            )
        )

        // when
        val result = CardScoreCalculator.isCloseToBlackjack(cards1, cards2)

        // then
        assertEquals(true, result)
    }

    @Test
    fun `두 cards를 비교할 때 첫번째 cards 점수가 blackjack에 더 가깝지 않다면 isCloseToBlackjack 메서드는 false를 반환한다`() {
        // given
        val cards1 = Cards(
            listOf(
                Card(Suit.SPADES, Denomination.TWO)
            )
        )
        val cards2 = Cards(
            listOf(
                Card(Suit.SPADES, Denomination.KING)
            )
        )

        // when
        val result = CardScoreCalculator.isCloseToBlackjack(cards1, cards2)

        // then
        assertEquals(false, result)
    }
}
