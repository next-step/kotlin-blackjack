package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayingCardsTest {
    @Test
    fun `PlayingCards는 PlayingCard 목록을 보관한다`() {
        val cards = listOf(
            PlayingCard(Suit.CLUBS, CardNumber.NINE),
            PlayingCard(Suit.HEARTS, CardNumber.ACE)
        )

        assertThat(PlayingCards.from(cards)).containsAll(cards)
    }

    @Test
    fun `empty를 통해 빈 PlayingCards를 얻을 수 있다`() {
        assertThat(PlayingCards.empty()).isEmpty()
    }

    @Test
    fun `더하기 연산자를 사용하여 새로운 PlayingCards를 얻을 수 있다`() {
        val cards1 = listOf(
            PlayingCard(Suit.CLUBS, CardNumber.NINE),
            PlayingCard(Suit.HEARTS, CardNumber.ACE)
        )
        val cards2 = listOf(
            PlayingCard(Suit.DIAMONDS, CardNumber.TEN),
            PlayingCard(Suit.SPADES, CardNumber.KING)
        )
        val playingCards = PlayingCards.from(cards1) + PlayingCards.from(cards2)

        assertAll(
            { assertThat(playingCards).containsAll(cards1) },
            { assertThat(playingCards).containsAll(cards2) }
        )
    }

    @Test
    fun `in 연산자를 통해 해당 번호를 가진 PlayingCard가 있는지 확인할 수 있다`() {
        val cards = listOf(
            PlayingCard(Suit.CLUBS, CardNumber.NINE),
            PlayingCard(Suit.HEARTS, CardNumber.ACE),
            PlayingCard(Suit.DIAMONDS, CardNumber.TEN),
            PlayingCard(Suit.SPADES, CardNumber.KING)
        )
        val playingCards = PlayingCards.from(cards)

        assertAll(
            { assertThat(CardNumber.NINE in playingCards).isTrue },
            { assertThat(CardNumber.ACE in playingCards).isTrue },
            { assertThat(CardNumber.TEN in playingCards).isTrue },
            { assertThat(CardNumber.KING in playingCards).isTrue },
            { assertThat(CardNumber.EIGHT in playingCards).isFalse }
        )
    }
}
