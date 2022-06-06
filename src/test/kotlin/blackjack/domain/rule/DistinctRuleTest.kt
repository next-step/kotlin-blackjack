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
                PlayingCard.of(Suit.CLUBS, CardNumber.NINE),
                PlayingCard.of(Suit.CLUBS, CardNumber.NINE),
                PlayingCard.of(Suit.DIAMONDS, CardNumber.TEN),
                PlayingCard.of(Suit.SPADES, CardNumber.KING)
            )
        )

        assertThat(DistinctRule.apply(playingCards)).containsOnlyOnce(PlayingCard.of(Suit.CLUBS, CardNumber.NINE))
    }
}
