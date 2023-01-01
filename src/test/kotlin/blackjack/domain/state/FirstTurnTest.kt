package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Shape
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

internal class FirstTurnTest {
    @Test
    fun `숫자의 총합이 21미만이면 Hit`() {
        val firstCard = Card(Shape.CLUB, Denomination.EIGHT)
        val secondCard = Card(Shape.CLUB, Denomination.TEN)
        val actual = FirstTurn.draw(firstCard, secondCard)

        actual.shouldBeInstanceOf<Hit>()
    }

    @Test
    fun `숫자의 총합이 21이라면 Blackjack`() {
        val firstCard = Card(Shape.CLUB, Denomination.ACE)
        val secondCard = Card(Shape.CLUB, Denomination.TEN)
        val actual = FirstTurn.draw(firstCard, secondCard)

        actual.shouldBeInstanceOf<Blackjack>()
    }
}
