package blackjack.dto

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GeneratePlayerRequestTest {

    @Test
    fun `베팅 금액이 0 이하가 되면 IllegalArgumentExcpetion을 throw 한다`() {
        assertThrows<IllegalArgumentException> {
            GeneratePlayerRequest("playerName", 0)
        }
    }
}
