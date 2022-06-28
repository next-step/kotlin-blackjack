package blackjack.domain

import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PlayerTest {
    @Test
    fun `플레이어 이름이 공백이면 예외`() {
        assertThrows<IllegalArgumentException> { Player("") }
            .shouldHaveMessage("플레이어 이름은 공백일 수 없습니다")
    }
}
