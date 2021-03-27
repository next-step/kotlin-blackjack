package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullAndEmptySource

class BlackJackGameTest {
    private val game = BlackJackGame()

    @Test
    fun `이름을 입력받아 플레이어를 반환한다`() {
        val players = game.parsePlayers("서진혁,nextstep,kotlin")
        assertThat(players.players.map { it.name }).containsAll(listOf("서진혁", "nextstep", "kotlin"))
    }

    @ParameterizedTest
    @NullAndEmptySource
    fun `이름을 입력하지 않으면 예외가 발생한다`(names: String?) {
        assertThrows<IllegalArgumentException> { game.parsePlayers(names) }
    }

    @Test
    fun `특정 이름이 공백이면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { game.parsePlayers("서진혁,,nextstep") }
    }
}
