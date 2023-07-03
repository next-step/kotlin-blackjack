package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Character
import blackjack.domain.card.Shape
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class BettingPlayerTest {

    @Test
    internal fun `딜러를 이기면 수익이 생긴다`() {
        val playerCard = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.EIGHT),
                Card(Shape.CLOVER, Character.TWO),
                Card(Shape.CLOVER, Character.A)
            )
        )
        val player = Player("pobi", playerCard)
        val bettings = BettingPlayer(player, 10000)
        val dealerCard = Cards(
            mutableListOf(
                Card(Shape.CLOVER, Character.J),
                Card(Shape.CLOVER, Character.SEVEN),
            )
        )
        val dealer = Dealer(dealerCard)
        bettings.getRevenue(dealer) shouldBe 10000
    }
}
