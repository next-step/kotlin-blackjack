package blackjack.domain.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class TrumpTest {
    @Test
    fun `카드는 중복 없이 52장으로 구성된다`() {
        val trump = Trump()
        trump.cards.size shouldBe 52
        trump.cards.toSet().size shouldBe 52
    }

    @Test
    fun `카드는 52장 뽑을 수 있다`() {
        val trump = Trump()
        val cards = mutableListOf<Card>()

        repeat(52) {
            cards.add(trump.getCard())
        }

        cards.size shouldBe 52

        // 52장 중복이 없는지 확인
        cards.toSet().size shouldBe 52

        shouldThrow<IllegalStateException> {
            trump.getCard()
        }
    }
}
