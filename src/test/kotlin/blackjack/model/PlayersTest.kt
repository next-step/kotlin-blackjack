package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayersTest {

    @Test
    fun `처음 카드를 배분하면 두 장씩 받는다`() {
        val players = Players(
            listOf(
                Player("a"),
                Player("b"),
                Player("c")
            )
        )

        players.initialCardDealing(Dealer())

        assertAll(
            { assertThat(players.values[0].cards).hasSize(2) },
            { assertThat(players.values[1].cards).hasSize(2) },
            { assertThat(players.values[2].cards).hasSize(2) },
        )
    }
}
