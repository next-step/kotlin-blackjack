package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrow
import org.junit.jupiter.api.Test

class BlackJackTableTest {

    @Test
    fun `게임 카드셋에 카드 중복은 없음`() {
        shouldNotThrow<IllegalStateException> {
            BlackJackTable.hitCard()
        }
    }
}
