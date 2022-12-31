package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Shape
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test

internal class BustTest {
    @Test
    fun `Bust이면 카드를 더 받을수 없습니다`() {
        val firstCard = Card(Shape.CLUB, Denomination.ACE)
        val secondCard = Card(Shape.CLUB, Denomination.TEN)
        val actual = FirstTurn.draw(firstCard, secondCard)

        shouldThrow<IllegalStateException> { actual.draw(firstCard) }.shouldHaveMessage("카드를 더 받을 수 없습니다.")
    }
}
