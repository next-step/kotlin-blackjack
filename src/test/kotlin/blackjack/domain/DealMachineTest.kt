package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DealMachineTest {
    @Test
    fun `처음에는 2장의 카드를 나눠준다`() {
        val players = Players("hong,lee")

        DealMachine.initialDeal(players)

        players.players.forEach {
            assertThat(it.cards.cards.size).isEqualTo(2)
        }
    }
}