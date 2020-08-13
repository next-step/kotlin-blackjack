package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `딜러가 처음 받은 2장의 합이 16이하일 경우`() {
        val dealer = Dealer().apply {
            requestCard(Card(Kinds.TEN, Shape.CLOVER))
            requestCard(Card(Kinds.SIX, Shape.CLOVER))
        }

        assertThat(dealer.checkIfGetExtraCardOrNot()).isEqualTo(true)
    }

    @Test
    fun `딜러가 처음 받은 2장의 합이 17이상일 경우`() {
        val dealer = Dealer().apply {
            requestCard(Card(Kinds.TEN, Shape.CLOVER))
            requestCard(Card(Kinds.SEVEN, Shape.CLOVER))
        }

        assertThat(dealer.checkIfGetExtraCardOrNot()).isEqualTo(false)
    }
}
