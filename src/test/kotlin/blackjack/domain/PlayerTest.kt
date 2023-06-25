package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Test

internal class PlayerTest{
    @Test
    internal fun `이름을 입력하면 Player가 생성된다`() {
        Player("pobi").name shouldBe "pobi"
    }

    @Test
    internal fun `빈값을 입력하면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Player("")
        }
    }
}