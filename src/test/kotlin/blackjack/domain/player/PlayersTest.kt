package blackjack.domain.player

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `getAllPlayers 를 호출할시 딜러를 포함한 모든 플레이어를 반환한다`() {
        val players = Players(
            dealer = Dealer(),
            guest = listOf(
                Player("one"),
                Player("two"),
            )
        )

        val actual = players.getAllPlayers()

        assertEquals(3, actual.size)
    }
}
