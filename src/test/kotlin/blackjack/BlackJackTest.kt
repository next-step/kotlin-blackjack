package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullAndEmptySource

class BlackJackTest {
    private val blackJack = BlackJack()

    @Test
    fun `이름을 입력받아 플레이어를 반환한다`() {
        val players = blackJack.parsePlayers("서진혁,nextstep,kotlin")
        assertThat(players.players.map { it.name }).isEqualTo(listOf("서진혁", "nextstep", "kotlin"))
    }

    @ParameterizedTest
    @NullAndEmptySource
    fun `이름을 입력하지 않으면 예외가 발생한다`(names: String?) {
        assertThrows<IllegalArgumentException> { blackJack.parsePlayers(names) }
    }

    @Test
    fun `특정 이름이 공백이면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { blackJack.parsePlayers("서진혁,,nextstep") }
    }

    @ParameterizedTest
    @NullAndEmptySource
    fun `응답을 입력하지 않으면 예외가 발생한다`(answer: String?) {
        assertThrows<IllegalArgumentException> { blackJack.parseAnswer(answer) }
    }

    @Test
    fun `응답을 y혹은 n이 아니면 예외가 발생한다`() {
        assertThrows<java.lang.IllegalArgumentException> { blackJack.parseAnswer("a") }
    }
}
