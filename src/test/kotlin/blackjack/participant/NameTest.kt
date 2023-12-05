package blackjack.participant

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class NameTest {

    @Test
    fun `빈 문자열은 이름이 될 수 없다`() {
        shouldThrow<IllegalArgumentException> { Name("") }
    }
}
