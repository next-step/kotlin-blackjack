package blackjack.domain

import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource

internal class PlayerTest {
    @DisplayName("Player 이름은 공백이 될 수 없다")
    @ParameterizedTest
    @EmptySource
    fun emptyName(name: String) {
        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy { Player(name) }
    }
}
