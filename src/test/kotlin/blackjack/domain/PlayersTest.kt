package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `플레이어 중 hit 의사결정을 내린 사람들만 필터링한다`() {
        // given
        val players = Players(
            listOf(
                Player("player1"),
                Player("player2"),
                Player("player3")
            )
        )

        // when
        players.values[0].turnStand()

        // then
        assertEquals(2, players.withHit().size)
    }
}
