package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `hit 가능한 플레이어들을 반환한다`() {
        val players = Players(
            listOf(
                Player("a").apply { noMoreHit() },
                Player("b"),
                Player("c").apply { noMoreHit() }
            )
        )
        val hitablePlayers = players.hitablePlayers()

        assertThat(hitablePlayers.size).isEqualTo(1)
        assertThat(hitablePlayers[0].name).isEqualTo("b")
    }
}
