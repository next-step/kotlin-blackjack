package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `플레이어들을 Names로 변환할 수 있다`() {
        val names = Names.from("laco")
        val players = Players.from(names)
        assertThat(players.toNames()).isEqualTo(names)
    }
}
