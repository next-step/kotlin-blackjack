package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test

internal class DeckTest {
    @Test
    fun `트럼프 카드는 52장으로 구성되며 뽑으면 카드 숫자가 줄어든다`() {
        val deck = Deck()

        repeat(52) {
            deck.draw()
        }

        shouldThrow<IllegalStateException> { deck.draw() }
            .shouldHaveMessage("카드를 전부 뽑았습니다.")
    }
}
