package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.Player
import blackjack.domain.SuitType
import blackjack.domain.ValueType
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
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
        assertThat(BlackjackGame("ace,con", CardDeck()).players.players.size)
            .isEqualTo(3)
    }

    @DisplayName("사용자 포인트 합 계산하기")
    @Test
    fun checkPointCalculationFromPlayer() {
        val player = Player("ace")
        player.addCard(Card(SuitType.CLUB, ValueType.FOUR))
        player.addCard(Card(SuitType.CLUB, ValueType.FIVE))
        player.addCard(Card(SuitType.CLUB, ValueType.K))
        assertThat(player.calculatePoint()).isEqualTo(19)
    }

    @DisplayName("사용자 포인트 합 계산하기")
    @Test
    fun checkPointCalculationFromPlayerWithAce() {
        val player = Player("ace")
        player.addCard(Card(SuitType.CLUB, ValueType.FOUR))
        player.addCard(Card(SuitType.CLUB, ValueType.FIVE))
        player.addCard(Card(SuitType.CLUB, ValueType.A))
        assertThat(player.calculatePoint()).isEqualTo(10)
        assertThat(player.calculatePoint(true)).isEqualTo(20)
    }

    @DisplayName("다음턴 사용자 확인")
    @Test
    fun checkNextTurn() {
        val blackjackGame = BlackjackGame("ace,hi,con,race", CardDeck())
        repeat(4) { blackjackGame.hitOrStay("y") }
        assertThat(blackjackGame.players.currentPlayer.name).isEqualTo("ace")
    }

    @DisplayName("다음턴 사용자 확인")
    @Test
    fun checkNextPlayer() {
        val blackjackGame = BlackjackGame("ace,hi,con,race", CardDeck())
        assertThat(blackjackGame.players.getNextPlayer()?.name).isEqualTo("hi")
    }

    @DisplayName("사용자가 카드 뽑고 나서 카드수")
    @Test
    fun checkPlayerCard() {
        val cardDeck = CardDeck()
        val blackjackGame = BlackjackGame("ace,hi,con,race", cardDeck)
        blackjackGame.players.currentPlayerPickCard(true, cardDeck)
        assertThat(blackjackGame.players.currentPlayer.cards.size)
            .isEqualTo(3)
    }
}
