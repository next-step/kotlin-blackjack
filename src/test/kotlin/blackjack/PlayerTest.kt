package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.Player
import blackjack.domain.SuitType
import blackjack.domain.ValueType
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {

    @DisplayName("플레이 입력값 확인")
    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["ace:con", "$", "ace,"])
    fun checkPlayersInput(playerNames: String) {
        Assertions.assertThatThrownBy { BlackjackGame(playerNames, CardDeck()) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @DisplayName("플레이어 수 확인")
    @Test
    fun checkPlayers() {
        Assertions.assertThat(BlackjackGame("ace,con", CardDeck()).players.size)
            .isEqualTo(2)
    }

    @DisplayName("사용자 포인트 합 계산하기")
    @Test
    fun checkPointCalculationFromPlayer() {
        val player = Player("ace")
        player.addCard(Card(SuitType.CLUB, ValueType.FOUR))
        player.addCard(Card(SuitType.CLUB, ValueType.FIVE))
        player.addCard(Card(SuitType.CLUB, ValueType.K))
        Assertions.assertThat(player.calculatePoint()).isEqualTo(19)
    }

    @DisplayName("사용자 포인트 합 계산하기")
    @Test
    fun checkPointCalculationFromPlayerWithAce() {
        val player = Player("ace")
        player.addCard(Card(SuitType.CLUB, ValueType.FOUR))
        player.addCard(Card(SuitType.CLUB, ValueType.FIVE))
        player.addCard(Card(SuitType.CLUB, ValueType.A))
        Assertions.assertThat(player.calculatePoint()).isEqualTo(20)
        Assertions.assertThat(player.calculatePoint(false)).isEqualTo(10)
    }
}
