package blackjack.domain

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = ["", "  "])
    fun `플레이어 이름으로 빈 값 전달시 예외 발생`(name: String) {
        assertThatThrownBy { Player(name) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("플레이어 이름은 빈 값일 수 없습니다.")
    }
}
