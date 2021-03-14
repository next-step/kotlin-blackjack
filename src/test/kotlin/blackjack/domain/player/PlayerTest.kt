package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class PlayerTest {

    @Test
    fun `플레이어 이름의 길이는 1글자 이상이다`() {
        assertDoesNotThrow { Player("1") }
    }

    @Test
    fun `플레이어 이름의 길이가 0 일 경우 예외를 던진다`() {
        assertThrows<IllegalArgumentException> { Player("") }
    }

    @Test
    fun `플레이어의 이름이 공백일 경우 예외를 던진다`() {
        assertThrows<IllegalArgumentException> { Player(" ") }
    }

    @Test
    fun `이름이 같을 경우 같은 플레이어로 취급한다`() {
        assertThat(Player("자손")).isEqualTo(Player("자손"))
    }
}
