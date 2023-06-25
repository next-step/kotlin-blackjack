package blackjack.domain.model

import blackjack.domain.model.Trump
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class TrumpTest {
    @Test
    fun `카드는 중복 없이 52장으로 구성된다`() {
        val trump = Trump()
        trump.cards.size shouldBe 52
        trump.cards.toSet().size shouldBe 52
    }
}
