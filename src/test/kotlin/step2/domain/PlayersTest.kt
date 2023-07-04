package step2.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `player 생성에 성공한다`() {
        // given
        val names = listOf("p1", "p2")

        // when
        val players = Players.of(names)

        // then
        assertThat(players.players.size).isSameAs(names.size)
    }
}
