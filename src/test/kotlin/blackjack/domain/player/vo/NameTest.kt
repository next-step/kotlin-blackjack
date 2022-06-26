package blackjack.domain.player.vo

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class NameTest {
    @ParameterizedTest
    @ValueSource(strings = ["", " ", "j", "j ", "jjjjjj"])
    fun `이름의 길이에는 제한이 있다`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { Name("") }
    }

    @Test
    fun `사용자는 이름을 가진다`() {
        val name = Name("장재주")
        assertThat(name.value).isEqualTo("장재주")
    }
}
