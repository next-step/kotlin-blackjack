package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Shape
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

internal class HitTest {
    @Test
    fun `새로 뽑은 카드까지 포함하여 21점을 초과하면 Bust로 변화한다`() {
        val state = Hit(Hand(Card(Shape.CLUB, Denomination.TEN), Card(Shape.CLUB, Denomination.TEN)))
        val actual = state.draw(Card(Shape.CLUB, Denomination.TWO))

        actual.shouldBeInstanceOf<Bust>()
    }

    @Test
    fun `새로 뽑은 카드까지 포함하여 21점미만이면 Hit이다`() {
        val state = Hit(Hand(Card(Shape.CLUB, Denomination.TWO), Card(Shape.CLUB, Denomination.THREE)))
        val actual = state.draw(Card(Shape.CLUB, Denomination.FOUR))

        actual.shouldBeInstanceOf<Hit>()
    }

    @Test
    fun `Stay로 변경할 수 있다`() {
        val state = Hit(Hand(Card(Shape.CLUB, Denomination.TWO), Card(Shape.CLUB, Denomination.THREE)))
        val actual = state.stay()

        actual.shouldBeInstanceOf<Stay>()
    }
}
