package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayersTest {
    @Test
    fun `입력한 이름으로 플레이어가 생성된다`() {
        val players = Players()
        players.generate(Names("a,b,c"))
        assertThat(players.get().keys.toString()).isEqualTo("[a, b, c]")
    }
}
