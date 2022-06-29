package blackjack.domain

import io.kotest.matchers.throwable.shouldHaveMessage
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class DeckTest {
    @Test
    fun `덱에서 카드를 드로우한다`() {
        val deck = Deck()

        val cards = (1..52).map { deck.draw() }

        assertThat(cards).containsExactlyInAnyOrder(
            *CardNumber.values().flatMap { cardNumber ->
                CardSuit.values().map { Card(cardNumber, it) }
            }.toTypedArray()
        )
    }

    @Test
    fun `드로우 할 카드가 없으면 예외`() {
        val deck = Deck()
        repeat(52) { deck.draw() }

        assertThrows<IllegalStateException> { deck.draw() }
            .shouldHaveMessage("더 이상 뽑을 패가 존재하지 않습니다.")
    }
}
