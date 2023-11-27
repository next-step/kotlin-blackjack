package blackjack.domain

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class NicknameTest {
    @ParameterizedTest
    @ValueSource(strings = ["", "js", "jsonjsonjson"])
    fun `닉네임이 3글자 미만이거나 10글자를 초과했을 경우 IllegalArgumentException 발생`(input: String) { // given
        assertThrows<IllegalArgumentException> { // then
            Nickname(input) // when
        }
    }
}