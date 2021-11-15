package blackjack.domain.state

import blackjack.domain.Denomination
import blackjack.domain.PlayingCard
import blackjack.domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("카드들(Cards)")
internal class PlayingCardsTest {

    @Test
    fun `카드들에는 빈값이 들어올 수 있다`() {
        val playingCards: PlayingCards = PlayingCards.initialize()

        assertAll(
            { assertThat(playingCards).isNotNull },
            { assertThat(playingCards).isExactlyInstanceOf(PlayingCards::class.java) },
        )
    }

    @Test
    fun `카드들에는 새로운 카드가 추가될 수 있다`() {
        val expected = PlayingCards.from(setOf(PlayingCard(Suit.SPADE, Denomination.ACE)))
        val playingCards: PlayingCards = PlayingCards.initialize()
        val actual = playingCards.plus(PlayingCard(Suit.SPADE, Denomination.ACE))

        assertThat(actual).isEqualTo(expected)
    }
}
