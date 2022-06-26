package camp.nextstep.blackjack.player

import camp.nextstep.blackjack.card.Card
import camp.nextstep.blackjack.card.CardNumber
import camp.nextstep.blackjack.card.CardSuit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

internal class GamblerTest {

    @DisplayName("베팅에 참가하는 플레이어는 이름을 가지고 있다.")
    @Test
    fun gamblerHasName() {
        val tim = Gambler("tim")
        assertThat(tim.name).isEqualTo("tim")
    }

    @DisplayName("베팅에 참가하는 플레이어의 이름은 공백일 수 없다.")
    @Test
    fun gamblerCannotHaveEmptyName() {
        val block = "\n"
        assertThrows<IllegalArgumentException> {
            Gambler(block)
        }
    }

    @DisplayName("플레이어의 처음 카드 패는 비어있다.")
    @Test
    fun newGamblerHasAnyCards() {
        val tim = Gambler("tim")

        assertThat(tim.hand.isEmpty()).isTrue
    }

    @DisplayName("플레이어는 카드를 받아 패에 추가할 수 있다.")
    @Test
    fun gamblerReceiveCard() {
        val tim = Gambler("tim")

        tim.receive(Card(CardSuit.SPADE, CardNumber.ACE))

        assertThat(tim.hand.cards).containsExactly(Card(CardSuit.SPADE, CardNumber.ACE))
    }
}
