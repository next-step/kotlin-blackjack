package blackjack.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class NameTest {
    @Test
    internal fun `이름은 생성될 때 주어진 문자열을 가지고 있다`() {
        val givenName = "이름"
        val name = Name(givenName)

        assertThat(name.value).isEqualTo(givenName)
    }

    @Test
    internal fun `이름은 객체가 달라도, 가진 값이 같다면 동일하다`() {
        val one = Name("name")
        val another = Name("name")

        assertThat(one).isEqualTo(another)
    }
}
