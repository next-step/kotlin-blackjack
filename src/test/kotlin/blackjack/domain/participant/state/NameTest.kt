package blackjack.domain.participant.state

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class NameTest {
    @ParameterizedTest
    @ValueSource(strings = ["pobi", "jason", "딜러"])
    fun `플레이어 이름 - 이름 생성 테스트`(name: String) {
        // when, then
        assertThat(Name(name)).isEqualTo(Name(name))
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "pobi ", "po bi", " pobi", "p  bi", "p b i"])
    fun `플레이어 이름 - 이름이 빈 문자열이거나 공백인 경우에 대한 예외처리 테스트`(name: String) {
        // when, then
        Assertions.assertThatThrownBy { Name(name) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("이름에 빈 문자열이거나 공백이 있습니다.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["pororo", "tongtongi", "crongcrong", "Hannah", "Christina", "Joannes"])
    fun `플레이어 이름 - 이름이 5자 초과인 경우에 대한 예외처리 테스트`(name: String) {
        // when, then
        Assertions.assertThatThrownBy { Name(name) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("이름은 5글자 이하만 가능합니다.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["pobi1", "1pobi", "pobi!", "pobi@", "pobi#", "po#bi"])
    fun `플레이어 이름 - 이름에 한글과 알파벳 이외의 문자가 있는 경우에 대한 예외처리 테스트`(name: String) {
        // when, then
        Assertions.assertThatThrownBy { Name(name) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("이름은 한글과 알파벳만 가능합니다.")
    }
}
