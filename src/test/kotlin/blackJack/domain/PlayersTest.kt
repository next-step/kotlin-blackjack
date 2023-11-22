package blackJack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `Player name List 입력하면 자동으로 Players 가 생성되고 배팅된다`() {
        val names = listOf("pobi", "jason")
        val players = Players.initBettings(names)
        Assertions.assertThat(players.players[0].name).isEqualTo("pobi")
        Assertions.assertThat(players.players.size).isEqualTo(2)
    }
}
