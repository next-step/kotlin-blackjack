package blackjack.domain.game

import blackjack.FinishedHand
import blackjack.domain.player.Dealer
import blackjack.domain.player.TestGamer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class GamerResultTest {

    @Test
    fun `플레이어의 결과로부터 딜러의 결과를 구한다`() {
        val dealer = Dealer()
        val playerResults = listOf(
            GamerResult(Profit(1000), TestGamer(FinishedHand)),
            GamerResult(Profit(-2000), TestGamer(FinishedHand)),
            GamerResult(Profit(3000), TestGamer(FinishedHand)),
            GamerResult(Profit(-4000), TestGamer(FinishedHand)),
        )

        val result = GamerResult.getDealerResultFromPlayers(dealer, playerResults)

        assertThat(result.profit.value).isEqualTo(2000)
    }
}
