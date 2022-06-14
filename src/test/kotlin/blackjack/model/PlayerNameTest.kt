package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("플레이어 이름 테스트")
class PlayerNameTest {

    @Test
    fun `플레이어 이름 정상 생성`() {
        // given, when, then
        assertThat(PlayerName("aiden").name).isEqualTo("aiden")
    }

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "  "])
    fun `플레이어 이름이 공백이면 예외 발생`(name: String) {
        // when, then
        val exception = assertThrows<IllegalArgumentException> { PlayerName(name) }
        assertThat(exception.message).isEqualTo("플레이어 이름이 공백일 수 없습니다.")
    }
}
