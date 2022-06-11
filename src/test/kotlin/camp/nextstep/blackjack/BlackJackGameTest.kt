package camp.nextstep.blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class BlackJackGameTest {

    @DisplayName("게임을 생성하면 카드 뭉치(Card Deck) 하나를 생성한다.")
    @Test
    fun newGame() {
        val newGame = BlackJackGame.new()

        assertThat(newGame.cardDeck.isNotEmpty).isTrue
    }
}
