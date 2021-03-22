package blackjack.domain.player

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class NameTest {

    @Test
    fun `name은 한 글자 이상이다 공백일 경우 생성 불가`() {
        assertThrows<IllegalArgumentException> { Name("") }
    }
}
