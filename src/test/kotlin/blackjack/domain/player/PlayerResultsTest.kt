package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerResultsTest {

    @Test
    fun `플레이어들의 결과를 생성할 수 있다`() {
        val profile1 = Profile.from(Name("player1"))
        val profile2 = Profile.from(Name("player2"))
        val givenGamer1 = Gamer(profile1)
        val givenGamer2 = Gamer(profile2)
        val players = Players.from(listOf(givenGamer1, givenGamer2, Dealer.of()))

        assertThat(PlayerResults(players, givenGamer1)).isNotNull
    }
}
