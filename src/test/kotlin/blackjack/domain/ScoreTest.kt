package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreTest {
    @Test
    fun `Score은 PlayingCards를 받아 현재 점수를 계산해 보관한다`() {
        val cards = listOf(
            PlayingCard.of(Suit.CLUBS, CardNumber.NINE),
            PlayingCard.of(Suit.HEARTS, CardNumber.EIGHT)
        )
        val playingCards = PlayingCards.from(cards)

        assertThat(Score.from(playingCards).value).isEqualTo(17)
    }

    @Test
    fun `ACE 카드의 점수를 11로 계산했을 때 총점이 21을 초과하지 않는다면, ACE는 11점으로 계산된다`() {
        val cards = listOf(
            PlayingCard.of(Suit.CLUBS, CardNumber.THREE),
            PlayingCard.of(Suit.HEARTS, CardNumber.FOUR),
            PlayingCard.of(Suit.HEARTS, CardNumber.ACE)
        )
        val playingCards = PlayingCards.from(cards)

        assertThat(Score.from(playingCards).value).isEqualTo(18)
    }

    @Test
    fun `ACE 카드의 점수를 11로 계산했을 때 총점이 21을 초과하면, ACE는 1점으로 계산된다`() {
        val cards = listOf(
            PlayingCard.of(Suit.CLUBS, CardNumber.KING),
            PlayingCard.of(Suit.HEARTS, CardNumber.JACK),
            PlayingCard.of(Suit.HEARTS, CardNumber.ACE)
        )
        val playingCards = PlayingCards.from(cards)

        assertThat(Score.from(playingCards).value).isEqualTo(21)
    }

    @Test
    fun `isBlackjack을 통해 현재 점수가 블랙잭인지 확인할 수 있다`() {
        val cards = listOf(
            PlayingCard.of(Suit.CLUBS, CardNumber.KING),
            PlayingCard.of(Suit.HEARTS, CardNumber.ACE)
        )
        val playingCards = PlayingCards.from(cards)

        assertThat(Score.from(playingCards).isBlackjack()).isTrue
    }

    @Test
    fun `isBust를 통해 현재 점수가 버스트인지 확인할 수 있다`() {
        val cards = listOf(
            PlayingCard.of(Suit.CLUBS, CardNumber.KING),
            PlayingCard.of(Suit.HEARTS, CardNumber.QUEEN),
            PlayingCard.of(Suit.HEARTS, CardNumber.JACK),
        )
        val playingCards = PlayingCards.from(cards)

        assertThat(Score.from(playingCards).isBust()).isTrue
    }

    @Test
    fun `canAddMore를 통해 카드를 더 가져올 수 있는 상태인지 확인할 수 있다`() {
        val cards = listOf(
            PlayingCard.of(Suit.CLUBS, CardNumber.NINE),
            PlayingCard.of(Suit.HEARTS, CardNumber.THREE),
        )
        val playingCards = PlayingCards.from(cards)

        assertThat(Score.from(playingCards).canAddMore()).isTrue
    }
}
