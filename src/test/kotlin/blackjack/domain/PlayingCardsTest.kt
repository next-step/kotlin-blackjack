package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayingCardsTest {
    @Test
    fun `PlayingCards는 PlayingCard 목록을 보관한다`() {
        val cards = listOf(
            PlayingCard.of(Suit.CLUBS, CardNumber.NINE),
            PlayingCard.of(Suit.HEARTS, CardNumber.ACE)
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
            PlayingCard.of(Suit.CLUBS, CardNumber.NINE),
            PlayingCard.of(Suit.HEARTS, CardNumber.ACE)
        )
        val cards2 = listOf(
            PlayingCard.of(Suit.DIAMONDS, CardNumber.TEN),
            PlayingCard.of(Suit.SPADES, CardNumber.KING)
        )
        val playingCards = PlayingCards.from(cards1) + PlayingCards.from(cards2)

        assertAll(
            { assertThat(playingCards).containsAll(cards1) },
            { assertThat(playingCards).containsAll(cards2) }
        )
    }

    @Test
    fun `countOf를 통해 해당 번호를 가진 PlayingCard가 몇 개 있는지 확인할 수 있다`() {
        val cards = listOf(
            PlayingCard.of(Suit.CLUBS, CardNumber.NINE),
            PlayingCard.of(Suit.HEARTS, CardNumber.ACE),
            PlayingCard.of(Suit.CLUBS, CardNumber.ACE),
            PlayingCard.of(Suit.DIAMONDS, CardNumber.TEN),
            PlayingCard.of(Suit.SPADES, CardNumber.KING)
        )
        val playingCards = PlayingCards.from(cards)

        assertAll(
            { assertThat(playingCards.countOf(CardNumber.NINE)).isOne },
            { assertThat(playingCards.countOf(CardNumber.ACE)).isEqualTo(2) },
            { assertThat(playingCards.countOf(CardNumber.TEN)).isOne },
            { assertThat(playingCards.countOf(CardNumber.KING)).isOne },
            { assertThat(playingCards.countOf(CardNumber.EIGHT)).isZero }
        )
    }
}
