package domain.player

import exception.IllegalPlayerNameException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PlayerNameTest {

    @DisplayName("비어있는 이름은 허용되어서는 안된다.")
    @ParameterizedTest
    @ValueSource(strings = ["", " ", "  ", "    "])
    fun constructor(name: String) {
        Assertions.assertThatExceptionOfType(IllegalPlayerNameException::class.java)
            .isThrownBy { PlayerName(name) }
    }
}
