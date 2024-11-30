package blackjack.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.junit.jupiter.api.Test

class BlackjackCardTest {
    @Test
    fun `예외로 Ace(A)는 1 또는 11로 계산할 수 있다`() {
        val ace = BlackjackCard.CardNumber.A

        with(ace.score) {
            this shouldContain 1
            this shouldContain 11
            this.size shouldBe 2
        }
    }

    @Test
    fun `King(K), Queen(Q), Jack(J)은 각각 10으로 계산한다`() {
        val king = BlackjackCard.CardNumber.K
        val queen = BlackjackCard.CardNumber.Q
        val jack = BlackjackCard.CardNumber.J

        listOf(king.score, queen.score, jack.score).forEach { score ->
            score shouldContain 10
            score.size shouldBe 1
        }
    }

    @Test
    fun `이미 사용된 카드는 재사용할 수 없다`() {
        val card = BlackjackCard.defaultSet.random()

        card.draw()

        val exception = shouldThrowExactly<IllegalStateException> {
            card.draw()
        }
        exception.message shouldContain "이미 사용된 카드입니다. | card: $card"
    }
}
