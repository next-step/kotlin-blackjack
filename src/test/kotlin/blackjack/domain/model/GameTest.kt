package blackjack.domain.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameTest {

    @Test
    fun `카드는 52장 뽑을 수 있다`() {
        val cards = mutableListOf<Card>()
        val game = Game()

        repeat(52) {
            cards.add(game.getCard())
        }

        cards.size shouldBe 52

        // 52장 중복이 없는지 확인
        cards.toSet().size shouldBe 52

        shouldThrow<IllegalStateException> {
            game.getCard()
        }
    }
}
