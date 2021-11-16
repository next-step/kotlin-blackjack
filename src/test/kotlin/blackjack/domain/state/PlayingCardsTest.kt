package blackjack.domain.state

import blackjack.domain.player.PlayingCards
import blackjack.domain.playingcard.Denomination
import blackjack.domain.playingcard.PlayingCard
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
    fun `카드들에 새로운 카드들이 추가될 수 있다`() {
        val externalPlayingCards = allPlayingCards()
        val playingCards: PlayingCards = PlayingCards.initialize()
        val actual = playingCards.plus(externalPlayingCards)
        val expected = PlayingCards.from(externalPlayingCards.toSet())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드들에 새로운 카드들이 추가될 때 중복된 카드가 들어오면 예외를 발생한다`() {
        val externalPlayingCards = allPlayingCards()
        val duplicatedPlayingCards = listOf(PlayingCard(Suit.CLUB, Denomination.ACE))
        val playingCards: PlayingCards = PlayingCards.initialize().plus(duplicatedPlayingCards)
        val exception = assertThrows<DuplicatePlayingCardException> { playingCards.plus(externalPlayingCards) }

        assertThat(exception.message).isEqualTo("이미 덱에 존재하는 카드가 있습니다.")
    }

    companion object {
        fun allPlayingCards(): List<PlayingCard> = Suit.values().flatMap { suit: Suit ->
            Denomination.values().map { denomination ->
                PlayingCard(suit, denomination)
            }
        }
    }
}
