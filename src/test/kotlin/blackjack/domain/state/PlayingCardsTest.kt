package blackjack.domain.state

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
}
