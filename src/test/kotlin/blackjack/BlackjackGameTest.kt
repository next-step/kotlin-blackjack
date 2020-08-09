package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.CardDeck
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class BlackjackGameTest {

    @DisplayName("플레이 입력값 확인")
    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["ace:con", "$", "ace,"])
    fun checkPlayersInput(playerNames: String) {
        assertThatThrownBy { BlackjackGame(playerNames, CardDeck()) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @DisplayName("플레이어 수 확인")
    @Test
    fun checkPlayers() {
        assertThat(BlackjackGame("ace,con", CardDeck()).players.size)
            .isEqualTo(2)
    }

    @DisplayName("게임에 사용될 카드 체크")
    @Test
    fun checkCards() {
        val blackjackGame = BlackjackGame("ace,con", CardDeck())
        assertThat(blackjackGame.getCardCount())
            .isEqualTo(52)
    }

    @DisplayName("첫번째 텀에 플레이어들에게 카드 두개씩 분배하기")
    @Test
    fun startGame() {
        val blackjackGame = BlackjackGame("ace,con", CardDeck())
        blackjackGame.startGame()
        assertThat(blackjackGame.getCurrentPosition())
            .isEqualTo(3)
    }
}
