package blackjack.domain.state

import blackjack.domain.player.PlayingCards
import blackjack.domain.playingcard.Denomination
import blackjack.domain.playingcard.PlayingCard
import blackjack.domain.playingcard.Suit
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
    fun `카드들에 새로운 카드들이 추가될 수 있다`() {
        val externalPlayingCards = Suit.values().flatMap { suit: Suit ->
            Denomination.values().map { denomination ->
                PlayingCard(suit, denomination)
            }
        }
        val playingCards: PlayingCards = PlayingCards.initialize()
        val actual = playingCards.plus(externalPlayingCards)
        val expected = PlayingCards.from(externalPlayingCards.toSet())

        assertThat(actual).isEqualTo(expected)
    }
}
