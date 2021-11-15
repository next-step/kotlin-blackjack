package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class NameTest {

    @ParameterizedTest
    @ValueSource(strings = ["a", "ab", "abc", "abcdefghijkmlnopqrstuvwxyz"])
    fun `공백이거나 널이 아닌 문자열로 이름을 만들 수 있다`(nameString: String) {
        val name = Name(nameString)

        assertAll(
            { assertThat(name).isNotNull },
            { assertThat(name).isExactlyInstanceOf(Name::class.java) },
        )
    }
}
