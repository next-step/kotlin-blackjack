package blackjack.domain.game

import blackjack.domain.card.TestCards
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class ResultsTest {
    @Test
    internal fun `딜러의 수익은 Player들의 수익을 반대로 합친금액이다`() {
        //given
        val playerCard = TestCards.getSeventeenPointCards()
        val player = Player("pobi", playerCard)
        val dealerCard = TestCards.getSixteenPointCards()
        val dealer = Dealer(dealerCard)

        //when
        player.bet(10000)
        val results = Results(mapOf(player to player.getRevenue(dealer)))

        //then
        results.getDealerResultAmount() shouldBe -10000
    }
}
