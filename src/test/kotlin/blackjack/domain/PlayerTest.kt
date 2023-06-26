package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어의 이름이 공백이면 예외가 발생한다`() {
        shouldThrow<IllegalArgumentException> { Player("") }
    }
}
