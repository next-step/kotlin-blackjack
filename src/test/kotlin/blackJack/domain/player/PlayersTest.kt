package blackJack.domain.player

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `Player name List 입력하면 자동으로 Players 와 초기 카드가 생성된다`() {
        val names = listOf("pobi", "jason").map { Player(it) }
        val players = Players.createPlayers(names)
        Assertions.assertThat(players.players[0].name).isEqualTo("pobi")
        Assertions.assertThat(players.players[1].name).isEqualTo("jason")
    }
}
