package blackjack.domain.state

import blackjack.domain.playingcard.Denomination
import blackjack.domain.playingcard.PlayingCard
import blackjack.domain.player.PlayingCards
import blackjack.domain.playingcard.Suit
import blackjack.error.DuplicatePlayingCardException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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

    @Test
    fun `기존에 들어있는 카드는 추가될 수가 없다`() {
        val spadeAce = PlayingCard(Suit.SPADE, Denomination.ACE)
        val playingCards: PlayingCards = PlayingCards.from(setOf(spadeAce))
        val exception = assertThrows<DuplicatePlayingCardException> {
            playingCards.plus(spadeAce)
        }

        assertThat(exception.message).isEqualTo("'%s_%s'는 이미 덱에 존재하는 카드입니다.".format(spadeAce.suit.name, spadeAce.denomination.name))
    }
}
