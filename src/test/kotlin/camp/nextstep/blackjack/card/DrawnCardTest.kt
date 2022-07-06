package camp.nextstep.blackjack.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class DrawnCardTest {

    @DisplayName("뽑힌 카드는 기본적으로 덮힌 상태이다.")
    @Test
    fun drawnCardShouldFaceDown() {
        val drawnCard = DrawnCard(Card(CardSuit.SPADE, CardNumber.ACE))

        assertThat(drawnCard.isFaceDown).isTrue
        assertThat(drawnCard.isFaceUp).isFalse
    }

    @DisplayName("덮힌 카드를 뒤집을 수 있다.")
    @Test
    fun reversibleDrawnCard() {
        val drawnCard = DrawnCard(Card(CardSuit.SPADE, CardNumber.ACE))

        drawnCard.turnUp()

        assertThat(drawnCard.isFaceDown).isFalse
        assertThat(drawnCard.isFaceUp).isTrue
    }
}
