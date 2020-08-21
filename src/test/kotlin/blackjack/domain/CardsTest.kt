package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CardsTest {
    private lateinit var cardsWithAce1: Cards
    private lateinit var cardsWithAce11: Cards
    private lateinit var expectedCards: Cards

    @BeforeEach
    fun setUp() {
        cardsWithAce1 = Cards(
            Card(CardScore.ACE, Suit.HEART),
            Card(CardScore.TWO, Suit.DIAMOND),
            Card(CardScore.TEN, Suit.CLUB)
        )
        cardsWithAce11 = Cards(Card(CardScore.ACE, Suit.HEART), Card(CardScore.TEN, Suit.DIAMOND))
        expectedCards = Cards(Card(CardScore.ACE, Suit.HEART), Card(CardScore.TWO, Suit.DIAMOND))
    }

    @Test
    fun `카드 크기`() {
        // when
        val size = cardsWithAce11.size
        // then
        assertThat(size).isEqualTo(2)
    }

    @Test
    fun `점수가 10보다 크면 ACE는 1로 계산한다`() {
        // when
        val score = cardsWithAce1.sumOfScores()

        // then
        assertThat(score).isEqualTo(13)
    }

    @Test
    fun `점수가 10 이하면 ACE는 11로 계산할 수 있다`() {
        // when
        val score = cardsWithAce11.sumOfScores()

        // then
        assertThat(score).isEqualTo(21)
    }
}
