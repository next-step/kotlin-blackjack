package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class DealerTest {
    @Test
    fun `딜러는 카드 총합이 16이하면 카드를 더 뽑아야 한다`() {
        val dealer = Dealer()
        dealer.addCard(Card(Shape.DIAMOND, Denomination.TEN))
        dealer.addCard(Card(Shape.DIAMOND, Denomination.SIX))

        dealer.canDraw() shouldBe true

        dealer.addCard(Card(Shape.DIAMOND, Denomination.ACE))

        dealer.canDraw() shouldBe false
    }
}
