package blackjack.model

import blackjack.dto.Money
import blackjack.model.Dealer.Companion.DEALER_NAME
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayersTest {

    @Test
    fun `처음 카드를 배분하면 두 장씩 받는다`() {
        val players = Players(
            listOf(
                Player("a", Money.ZERO),
                Player("b", Money.ZERO),
                Player("c", Money.ZERO)
            )
        )

        players.initialCardDealing(Dealer(DEALER_NAME, Money.ZERO))

        assertAll(
            { assertThat(players.values[0].cards).hasSize(2) },
            { assertThat(players.values[1].cards).hasSize(2) },
            { assertThat(players.values[2].cards).hasSize(2) },
        )
    }
}
