package blackjack.domain

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = ["a,a", "a,b,a", "a,b,b"])
    fun `플레이어는 유일한 이름을 가진다`(namesInput: String) {
        // given
        val names = namesInput.split(",").toTypedArray()

        // when, then
        assertThrows<IllegalArgumentException> { Players(*names) }
    }
}
