package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("카드(PlayingCard)")
internal class PlayingCardTest {

    @Test
    fun `카드는 슈트와 끗수로 이루어진다`() {
        val playingCard = PlayingCard(Suit.CLUB, Denomination.ACE)

        assertAll(
            { assertThat(playingCard).isNotNull },
            { assertThat(playingCard).isExactlyInstanceOf(PlayingCard::class.java) },
        )
    }
}
