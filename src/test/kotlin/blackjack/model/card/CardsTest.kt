package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CardsTest {
    private lateinit var ACE_CLUB: Card
    private lateinit var TEN_CLUB: Card
    private lateinit var TEN_HEART: Card
    private lateinit var cardsWithScore20: Cards
    private lateinit var blackJackCards: Cards

    @BeforeEach
    fun setUp() {
        ACE_CLUB = Card(CardScore.ACE, Suit.CLUB)
        TEN_CLUB = Card(CardScore.TEN, Suit.CLUB)
        TEN_HEART = Card(CardScore.TEN, Suit.HEART)

        cardsWithScore20 = Cards(TEN_CLUB, TEN_HEART)
        blackJackCards = Cards(ACE_CLUB, TEN_CLUB)
    }

    @Test
    fun `카드 1장 더하기`() {
        // given
        val expected = Cards(ACE_CLUB, TEN_CLUB, TEN_HEART)

        // when
        val actual = cardsWithScore20 + ACE_CLUB

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `점수 확인`() {
        // when
        val actual = blackJackCards.score()

        // then
        assertThat(actual).isEqualTo(21)
    }

    @Test
    fun `두 장인지 확인`() {
        // when
        val actual = blackJackCards.isSizeOfTwo()

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `BlackJack인지 확인`() {
        // given
        val threeSizeCards = Cards(ACE_CLUB, TEN_CLUB, TEN_HEART)

        // when, then
        assertThat(cardsWithScore20.isBlackJack()).isFalse()
        assertThat(threeSizeCards.isBlackJack()).isFalse()
        assertThat(blackJackCards.isBlackJack()).isTrue()
    }

    @Test
    fun `Bust인지 확인`() {
        // given
        val bustCards = cardsWithScore20 + Card(CardScore.TWO, Suit.HEART)

        // when, then
        assertThat(cardsWithScore20.isBust()).isFalse()
        assertThat(bustCards.isBust()).isTrue()
    }
}
