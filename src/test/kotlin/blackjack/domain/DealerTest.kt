package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Shape
import blackjack.domain.state.FirstTurn
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class DealerTest {
    @Test
    fun `딜러는 카드 총합이 16이하면 카드를 더 뽑아야 한다`() {
        val firstCard = Card(Shape.DIAMOND, Denomination.TEN)
        val secondCard = Card(Shape.DIAMOND, Denomination.SIX)

        val dealer = Dealer(FirstTurn.draw(firstCard, secondCard))

        dealer.draw(secondCard)

        dealer.canDraw() shouldBe true

        dealer.draw(Card(Shape.DIAMOND, Denomination.ACE))

        dealer.canDraw() shouldBe false
    }
}
