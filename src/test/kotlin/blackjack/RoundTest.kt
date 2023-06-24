package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.lang.IllegalStateException

class RoundTest {

    @Test
    fun `카드는 52장 뽑을 수 있다`() {
        val cards = mutableListOf<Card>()
        val round = Round()

        repeat(52) {
            cards.add(round.getCard())
        }

        cards.size shouldBe 52

        // 52장 중복이 없는지 확인
        cards.toSet().size shouldBe 52

        shouldThrow<IllegalStateException> {
            round.getCard()
        }
    }
}
