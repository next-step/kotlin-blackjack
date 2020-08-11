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

        assertThat(dealer.myReceivedCard.size).isEqualTo(3)
    }

    @Test
    fun `딜러가 처음 받은 2장의 합이 17이상일 경우`() {
        val dealer = Dealer().apply {
            requestCard(Card(Kinds.TEN, Shape.CLOVER))
            requestCard(Card(Kinds.SEVEN, Shape.CLOVER))
        }

        assertThat(dealer.myReceivedCard.size).isEqualTo(2)
    }
}

class Dealer : Gamer() {

    override fun requestCard(card: Card) {
        super.requestCard(card)
        requestOneOfCardIfAvailable()
    }

    private fun requestOneOfCardIfAvailable() {
        if (checkIfGetExtraCardOrNot()) super.requestCard(Card.pop())
    }

    private fun checkIfGetExtraCardOrNot(): Boolean =
        myReceivedCard.size == 2 && calculatePoint() <= EXTRA_CARD_AVAILABLE_LIMIT_POINT

    companion object {
        private const val EXTRA_CARD_AVAILABLE_LIMIT_POINT = 16
    }
}
