package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerResultsTest {

    @Test
    fun `플레이어들의 결과를 생성할 수 있다`() {
        val givenGamer1 = Gamer(Name("player1"))
        val givenGamer2 = Gamer(Name("player2"))
        val players = Players(listOf(givenGamer1, givenGamer2))

        assertThat(PlayerResults(players, givenGamer1)).isNotNull
    }
}
