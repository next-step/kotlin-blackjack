package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PlayerNamesTest {

    @Test
    fun `플레이어 이름 생성 가능하다`() {
        val names = listOf("black", "jack")
        val result = PlayerNames(names)
        assertThat(result).isNotNull
    }

    @Test
    fun `플레이어 이름에 중복된 이름이 있는 경우 예외를 반환한다`() {
        val names = listOf("black", "black", "jack")
        assertThrows<IllegalArgumentException> { PlayerNames(names) }
    }
}
