package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class CardNumberTest {

    @Test
    fun `등록되지 않은 번호는 예외 발생`() {
        shouldThrow<IllegalArgumentException> { CardNumber.of("16") }
    }
}
