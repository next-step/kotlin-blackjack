package camp.nextstep.blackjack.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardTest {

    @DisplayName("카드는 모양과 숫자로 이루어져있다.")
    @Test
    fun cardProperties() {
        val spadeAce = Card(CardSuit.SPADE, CardNumber.ACE)

        val (suit, cardNumber) = spadeAce

        assertThat(suit).isEqualTo(CardSuit.SPADE)
        assertThat(cardNumber).isEqualTo(CardNumber.ACE)
    }

    @DisplayName("카드 모양과 숫자가 같으면 같은 카드이다.")
    @Test
    fun cardEquality() {
        val spadeAce1 = Card(CardSuit.SPADE, CardNumber.ACE)
        val spadeAce2 = Card(CardSuit.SPADE, CardNumber.ACE)

        assertThat(spadeAce1).isEqualTo(spadeAce2)
    }
}
