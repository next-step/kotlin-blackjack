package blackjack.domain.player.name

import blackjack.error.InvalidPlayerNameException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("이름(Name)")
internal class NameTest {

    @ParameterizedTest(name = "입력값 : {0}")
    @ValueSource(strings = ["a", "ab", "abc", "abcdefghijkmlnopqrstuvwxyz"])
    internal fun `이름은 공백이거나 널이 아닌 문자열로 이루어져 있다`(nameString: String) {
        val name = Name(nameString)

        assertAll(
            { assertThat(name).isNotNull },
            { assertThat(name).isExactlyInstanceOf(Name::class.java) },
        )
    }

    @ParameterizedTest(name = "입력값 : {0}")
    @EmptySource
    internal fun `이름은 공백으로 이루어지면 안 된다`(nameString: String) {
        val exception = assertThrows<InvalidPlayerNameException> { Name(nameString) }

        assertThat(exception.message).isEqualTo("'%s'는 유효한 이름이 아닙니다.".format(nameString))
    }
}
