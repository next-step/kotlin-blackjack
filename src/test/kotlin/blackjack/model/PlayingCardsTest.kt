package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayingCardsTest {
    @Test
    fun `PlayingCards는 PlayingCard List를 보관한다`() {
        val cards = listOf(
            PlayingCard(Suit.CLOVER, CardNumber.NINE),
            PlayingCard(Suit.HEART, CardNumber.ACE)
        )
        val playingCards = PlayingCards(cards)

        assertThat(playingCards).containsAll(cards)
    }
}
