package blackjack.domain.rule

import blackjack.domain.CardNumber
import blackjack.domain.PlayingCard
import blackjack.domain.PlayingCards
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DistinctRuleTest {
    @Test
    fun `DistinctRule을 적용하여 중복되는 카드를 제거할 수 있다`() {
        val playingCards = PlayingCards.from(
            listOf(
                PlayingCard(Suit.CLUBS, CardNumber.NINE),
                PlayingCard(Suit.CLUBS, CardNumber.NINE),
                PlayingCard(Suit.DIAMONDS, CardNumber.TEN),
                PlayingCard(Suit.SPADES, CardNumber.KING)
            )
        )

        assertThat(DistinctRule.applyTo(playingCards)).containsOnlyOnce(PlayingCard(Suit.CLUBS, CardNumber.NINE))
    }
}
