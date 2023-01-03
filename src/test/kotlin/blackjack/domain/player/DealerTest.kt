package blackjack.domain.player

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

        dealer.canDraw() shouldBe true
    }

    @Test
    fun `딜러는 카드 총합이 16초과하면 카드를 더 뽑지 않는다`() {
        val firstCard = Card(Shape.DIAMOND, Denomination.TEN)
        val secondCard = Card(Shape.DIAMOND, Denomination.SEVEN)

        val dealer = Dealer(FirstTurn.draw(firstCard, secondCard))

        dealer.canDraw() shouldBe false
    }
}
