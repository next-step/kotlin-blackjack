package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class CardTest {
    @Test
    fun `카드 점수`() {
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.TWO).optimizeScore(0)).isEqualTo(2)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.THREE).optimizeScore(0)).isEqualTo(3)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.FOUR).optimizeScore(0)).isEqualTo(4)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.FIVE).optimizeScore(0)).isEqualTo(5)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.SIX).optimizeScore(0)).isEqualTo(6)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.SEVEN).optimizeScore(0)).isEqualTo(7)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.EIGHT).optimizeScore(0)).isEqualTo(8)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.NINE).optimizeScore(0)).isEqualTo(9)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.TEN).optimizeScore(0)).isEqualTo(10)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.KING).optimizeScore(0)).isEqualTo(10)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.JACK).optimizeScore(0)).isEqualTo(10)
        assertThat(Card.of(CardSuit.CLUBS, CardSpell.QUEEN).optimizeScore(0)).isEqualTo(10)
    }

    @ParameterizedTest
    @CsvSource(
        "20, 1", "11, 1",
        "1, 11", "2, 11",
        "3, 11", "4, 11",
        "5, 11", "6, 11",
        "7, 11", "8, 11",
        "9, 11", "10, 11"
    )
    fun `에이스 카드 점수`(total: Int, expectedScore: Int) {
        val card = Card.of(CardSuit.CLUBS, CardSpell.ACE)

        assertThat(card.optimizeScore(total)).isEqualTo(expectedScore)
    }

}