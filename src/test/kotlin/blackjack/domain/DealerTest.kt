package blackjack.domain

import blackjack.domain.state.started.Hit
import blackjack.domain.state.started.finished.Stay
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class DealerTest {
    @Test
    fun stay() {
        val cards = Cards(listOf(Card(CardShape.SPADE, CardType.TWO), Card(CardShape.SPADE, CardType.THREE)))
        val dealer = Dealer(Hit(cards))

        dealer.stay()
        Assertions.assertThat(dealer.state).isInstanceOf(Stay::class.java)
    }
}