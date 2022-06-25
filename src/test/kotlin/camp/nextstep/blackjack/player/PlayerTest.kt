package camp.nextstep.blackjack.player

import camp.nextstep.blackjack.card.Card
import camp.nextstep.blackjack.card.CardNumber
import camp.nextstep.blackjack.card.CardSuit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

internal class PlayerTest {

    @DisplayName("플레이어는 이름을 가지고 있다.")
    @Test
    fun playerHasName() {
        val tim = Player("tim")
        assertThat(tim.name).isEqualTo("tim")
    }

    @DisplayName("플레이어 이름은 공백일 수 없다.")
    @Test
    fun playerCannotHaveEmptyName() {
        val block = "\n"
        assertThrows<IllegalArgumentException> {
            Player(block)
        }
    }

    @DisplayName("플레이어의 처음 카드 패는 비어있다.")
    @Test
    fun newPlayerHasAnyCards() {
        val tim = Player("tim")
        assertThat(tim.hand.isEmpty()).isTrue
    }

    @DisplayName("플레이어는 카드를 받아 패에 추가할 수 있다.")
    @Test
    fun playerReceiveCard() {
        val tim = Player("tim")
        tim.receive(Card(CardSuit.SPADE, CardNumber.ACE))
        assertThat(tim.hand.cards).containsExactly(Card(CardSuit.SPADE, CardNumber.ACE))
    }
}
