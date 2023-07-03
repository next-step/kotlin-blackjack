package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Character
import blackjack.domain.card.Shape
import blackjack.domain.card.TestCards
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class ResultsTest {
    @Test
    internal fun `딜러의 수익은 Player들의 수익을 반대로 합친금액이다`() {
        val playerCard = TestCards.getSeventeenPointCards()
        val dealerCard = TestCards.getSixteenPointCards()
        val player = Player("pobi", playerCard)
        player.bet(10000)
        val dealer = Dealer(dealerCard)
        val results = Results(mapOf(player to player.getRevenue(dealer)))
        results.getDealerResultAmount() shouldBe -10000
    }
}
