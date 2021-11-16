package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.ValueSource

internal class NameTest {
    @ParameterizedTest
    @ValueSource(strings = ["test", "test2", "test3"])
    fun `이름을 생성할 수 있다`(givenName: String) {
        val name = Name(givenName)

        assertThat(name).isNotNull
    }

    @ParameterizedTest
    @NullAndEmptySource
    fun `빈 값을 이름으로 생성하면 예외를 던진다`(givenName: String?) {
        assertThrows<IllegalArgumentException> { Name(givenName) }
    }
}
