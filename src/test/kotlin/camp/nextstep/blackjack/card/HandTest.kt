package camp.nextstep.blackjack.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class HandTest {

    @DisplayName("카드 패는 처음에 카드가 없다.")
    @Test
    fun newHandDoesNotHaveCard() {
        val hand = Hand()
        assertThat(hand.cards).isEmpty()
    }

    @DisplayName("카드 패에는 카드를 한 장씩 추가할 수 있다.")
    @Test
    fun handAddCard() {
        val hand = Hand()

        hand.add(DrawnCard(Card(CardSuit.SPADE, CardNumber.ACE)))

        assertThat(hand.cards).hasSize(1)
        assertThat(hand.cards[0].card).isEqualTo(Card(CardSuit.SPADE, CardNumber.ACE))
    }
}
