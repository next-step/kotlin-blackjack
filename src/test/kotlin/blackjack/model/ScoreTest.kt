package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreTest {
    @Test
    fun `Score은 PlayingCards를 받아 현재 점수를 계산해 보관한다`() {
        val cards = listOf(
            PlayingCard.of(Suit.CLUBS, CardNumber.NINE),
            PlayingCard.of(Suit.HEARTS, CardNumber.ACE)
        )
        val playingCards = PlayingCards.from(cards)

        assertThat(Score.from(playingCards).value).isEqualTo(20)
    }
}
