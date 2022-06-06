package blackjack.domain.rule

import blackjack.domain.CardNumber
import blackjack.domain.PlayingCard
import blackjack.domain.PlayingCards
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ShuffleRuleTest {
    @Test
    fun `ShuffleRule을 사용하여 카드를 섞을 수 있다`() {
        val playingCards = PlayingCards.from(
            listOf(
                PlayingCard.of(Suit.CLUBS, CardNumber.NINE),
                PlayingCard.of(Suit.HEARTS, CardNumber.NINE),
                PlayingCard.of(Suit.DIAMONDS, CardNumber.TEN),
                PlayingCard.of(Suit.SPADES, CardNumber.KING)
            )
        )

        assertThat(ShuffleRule.applyTo(playingCards)).containsAll(playingCards)
    }
}
