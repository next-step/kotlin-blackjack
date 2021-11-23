package blackjack.state

import blackjack.model.Amount
import blackjack.model.Card
import blackjack.model.Cards
import blackjack.model.Denomination
import blackjack.model.Profit
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StayTest {

    @Test
    fun `상대의 상태가 Bust면 1배 수익을 낸다`() {
        val dealer = Bust(Cards.empty())
        val player = Stay(Cards.empty())

        assertThat(player.profit(Amount(10000.0), dealer))
            .isEqualTo(Profit(Amount(10000.0)))
    }

    @Test
    fun `내 카드의 합이 상대보다 크면 1배 수익을 낸다`() {
        val dealer = Stay(
            Cards(
                listOf(
                    Card(Denomination.TEN, Suit.HEART),
                    Card(Denomination.FIVE, Suit.HEART),
                )
            )
        )
        val player = Stay(
            Cards(
                listOf(
                    Card(Denomination.TEN, Suit.CLOVER),
                    Card(Denomination.SIX, Suit.CLOVER),
                )
            )
        )
        assertThat(player.profit(Amount(10000.0), dealer))
            .isEqualTo(Profit(Amount(10000.0)))
    }

    @Test
    fun `내 카드의 합이 상대보다 작으면 -1배 수익이 된다`() {
        val player = Stay(
            Cards(
                listOf(
                    Card(Denomination.TEN, Suit.HEART),
                    Card(Denomination.FIVE, Suit.HEART),
                )
            )
        )
        val dealer = Stay(
            Cards(
                listOf(
                    Card(Denomination.TEN, Suit.CLOVER),
                    Card(Denomination.SIX, Suit.CLOVER),
                )
            )
        )
        assertThat(player.profit(Amount(10000.0), dealer))
            .isEqualTo(Profit(Amount(-10000.0)))
    }
}
