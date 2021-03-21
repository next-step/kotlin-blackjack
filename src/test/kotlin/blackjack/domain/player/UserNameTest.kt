package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class UserNameTest {
    @DisplayName("이름 생성")
    @ParameterizedTest
    @ValueSource(strings = ["1", "123456789", "name123"])
    fun constructor(input: String) {
        assertDoesNotThrow { UserName(input) }.also { assertThat(it).isNotNull }
    }

    @DisplayName("이름이 10글자 이상인 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = ["", "1234567890"])
    fun validateLength(input: String) {
        assertThrows<IllegalArgumentException> { UserName(input) }
    }

    @DisplayName("이름에 공백이 포함된 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = [" ", "a b"])
    fun validateContainBlank(input: String) {
        assertThrows<IllegalArgumentException> { UserName(input) }
    }
}
