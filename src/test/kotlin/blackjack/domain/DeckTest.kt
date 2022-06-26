package blackjack.domain

import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class DeckTest {
    @Test
    fun `드로우 할 카드가 없으면 예외`() {
        val deck = Deck()
        repeat(52) { deck.draw() }

        assertThrows<IllegalStateException> { deck.draw() }
            .shouldHaveMessage("더 이상 뽑을 패가 존재하지 않습니다.")
    }
}
