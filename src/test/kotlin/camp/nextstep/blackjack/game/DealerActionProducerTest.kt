package camp.nextstep.blackjack.game

import camp.nextstep.blackjack.card.Card
import camp.nextstep.blackjack.card.CardNumber
import camp.nextstep.blackjack.card.CardSuit
import camp.nextstep.blackjack.player.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class DealerActionProducerTest {

    @DisplayName("딜러의 패의 합계가 16 이하이면 HIT 해야한다.")
    @Test
    fun dealerShouldHitWhenUnder16() {
        val dealer = Dealer()
        dealer.receive(Card(CardSuit.SPADE, CardNumber.TEN))
        dealer.receive(Card(CardSuit.SPADE, CardNumber.SIX))

        assertThat(DealerActionProducer.produce(dealer)).isEqualTo(Action.HIT)
    }

    @DisplayName("딜러의 패의 합계가 16 보다 크면 STAY 해야한다.")
    @Test
    fun dealerShouldStayWhenOver16() {
        val dealer = Dealer()
        dealer.receive(Card(CardSuit.SPADE, CardNumber.TEN))
        dealer.receive(Card(CardSuit.SPADE, CardNumber.SEVEN))

        assertThat(DealerActionProducer.produce(dealer)).isEqualTo(Action.STAY)
    }
}
