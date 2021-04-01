package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullAndEmptySource

class BlackJackGameTest {
    private val game = BlackJackGame()

    @Test
    fun `이름을 입력받아 ,단위로 split하여 이름을 반환한다`() {
        val players = game.parseName("서진혁,nextstep,kotlin")
        assertThat(players).containsAll(listOf("서진혁", "nextstep", "kotlin"))
    }

    @ParameterizedTest
    @NullAndEmptySource
    fun `이름을 입력하지 않으면 예외가 발생한다`(names: String?) {
        assertThrows<IllegalArgumentException> { game.parseName(names) }
    }

    @Test
    fun `특정 이름이 공백이면 예외가 발생한다`() {
        val names = game.parseName("서진혁,,nextstep")
        assertThrows<IllegalArgumentException> { game.parsePlayers(names, listOf(1, 2, 3)) }
    }
}
