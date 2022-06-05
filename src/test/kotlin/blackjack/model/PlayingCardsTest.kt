package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayingCardsTest {
    @Test
    fun `PlayingCards는 PlayingCard 목록을 보관한다`() {
        val cards = listOf(
            PlayingCard(Suit.CLUBS, CardNumber.NINE),
            PlayingCard(Suit.HEARTS, CardNumber.ACE)
        )
        assertThat(PlayingCards.from(cards)).containsAll(cards)
    }
}
