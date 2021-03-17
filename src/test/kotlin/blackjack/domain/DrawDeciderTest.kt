package blackjack.domain

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class DrawDeciderTest {
    @DisplayName("y 또는 n인 경우 인스턴스 반환")
    @ParameterizedTest
    @ValueSource(strings = ["y", "n"])
    fun of(input: String) {
        assertDoesNotThrow { DrawDecider.of(input) }
    }

    @DisplayName("y 또는 n이 아닌 경우 예외 발생")
    @Test
    fun of_exception() {
        assertThrows<IllegalArgumentException> { DrawDecider.of("a") }
    }
}
