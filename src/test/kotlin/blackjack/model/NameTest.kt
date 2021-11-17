package blackjack.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NameTest {

    @Test
    fun `이름이 비어 있으면 RuntimeException 예외가 발생한다`() {
        assertThrows<RuntimeException> { Name.valueOf("") }
    }
}
