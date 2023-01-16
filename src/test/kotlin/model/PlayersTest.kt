package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayersTest {
    @Test
    fun `플레이어가 추가된다`() {
        val players = Players()
        assertThat(players.add(Player("Kim"))).isTrue
    }

    @Test
    fun `플레이어 목록을 가져온다`() {
        val players = Players()
        val player = Player("Kim")
        players.add(player)
        assertThat(players.get()[0]).isEqualTo(player)
    }
}
