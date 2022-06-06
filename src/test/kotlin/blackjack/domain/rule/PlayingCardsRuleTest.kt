package blackjack.domain.rule

import blackjack.domain.CardNumber
import blackjack.domain.PlayingCard
import blackjack.domain.PlayingCards
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayingCardsRuleTest {
    @Test
    fun `PlayingCardsRule을 이용해 PlayingCards에 룰을 적용할 수 있다`() {
        val playingCards = PlayingCards.from(
            listOf(
                PlayingCard.of(Suit.CLUBS, CardNumber.NINE),
                PlayingCard.of(Suit.HEARTS, CardNumber.ACE),
                PlayingCard.of(Suit.DIAMONDS, CardNumber.TEN),
                PlayingCard.of(Suit.SPADES, CardNumber.KING)
            )
        )
        val playingCardsRule = object : PlayingCardsRule {
            override fun applyTo(playingCards: PlayingCards): PlayingCards {
                val cards = playingCards.filterNot { playingCard ->
                    playingCard.suit == Suit.CLUBS
                }
                return PlayingCards.from(cards)
            }
        }

        assertThat(playingCardsRule.applyTo(playingCards))
            .doesNotContain(PlayingCard.of(Suit.CLUBS, CardNumber.NINE))
            .containsAll(
                listOf(
                    PlayingCard.of(Suit.HEARTS, CardNumber.ACE),
                    PlayingCard.of(Suit.DIAMONDS, CardNumber.TEN),
                    PlayingCard.of(Suit.SPADES, CardNumber.KING)
                )
            )
    }
}
