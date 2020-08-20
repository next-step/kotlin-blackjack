package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.domain.HIT
import blackjack.domain.PlayResultType
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.SuitType
import blackjack.domain.ValueType
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
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
        assertThatThrownBy { Players.from(playerNames) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @DisplayName("플레이어 수 확인")
    @Test
    fun checkPlayers() {
        val players = Players.from("ace,con")
        assertThat(BlackjackGame(players).players.players.size)
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
        assertThat(player.calculatePoint(true)).isEqualTo(20)
    }

    @DisplayName("다음턴 사용자 확인")
    @Test
    fun checkNextTurn() {
        val players = Players.from("ace,hi,con,race")
        val blackjackGame = BlackjackGame(players)
        repeat(4) { blackjackGame.hitOrStay(HIT) }
        assertThat(blackjackGame.players.currentPlayer.name).isEqualTo("ace")
    }

    @DisplayName("사용자가 카드 뽑고 나서 카드수")
    @Test
    fun checkPlayerCard() {
        val cardDeck = CardDeck()
        val players = Players.from("ace,hi,con,race")
        val blackjackGame = BlackjackGame(players, cardDeck)
        blackjackGame.players.currentPlayerPickCard(true, cardDeck)
        blackjackGame.startGame()

        assertThat(blackjackGame.players.currentPlayer.cards.size)
            .isEqualTo(3)
    }

    @DisplayName("플레이어의 승리 확인")
    @Test
    fun checkPlayerWin() {
        val players = Players.from("ace,hi,con")
        val onlyPlayers = players.players.filterNot { it is Dealer }
        repeat(3) { players.players[0].addCard(Card(SuitType.CLUB, ValueType.EIGHT)) }
        repeat(2) { onlyPlayers.forEach { it.addCard(Card(SuitType.CLUB, ValueType.K)) } }
        players.calculateResult()

        val winCount = onlyPlayers.count { it.playResult == PlayResultType.WIN }

        assertThat(winCount).isEqualTo(onlyPlayers.count())
    }

    @DisplayName("플레이어의 블랙잭 확인")
    @Test
    fun checkPlayerBlackjack() {
        val players = Players.from("ace,hi,con")
        val onlyPlayers = players.players.filterNot { it is Dealer }
        onlyPlayers.forEach {
            it.addCard(Card(SuitType.CLUB, ValueType.Q))
            it.addCard(Card(SuitType.CLUB, ValueType.A))
        }
        players.calculateResult(18)

        val blackJackCount = onlyPlayers.count { it.playResult == PlayResultType.BLACKJACK }

        assertThat(blackJackCount).isEqualTo(onlyPlayers.count())
    }

    @DisplayName("플레이어의 패배 확인")
    @Test
    fun checkPlayerLose() {
        val players = Players.from("ace,hi,con")
        val onlyPlayers = players.players.filterNot { it is Dealer }
        repeat(2) { players.players[0].addCard(Card(SuitType.CLUB, ValueType.NINE)) }
        repeat(3) { onlyPlayers.forEach { it.addCard(Card(SuitType.CLUB, ValueType.K)) } }
        players.calculateResult()

        val loseCount = onlyPlayers.count { it.playResult == PlayResultType.LOSE }

        assertThat(loseCount).isEqualTo(onlyPlayers.count())
    }

    @DisplayName("플레이어의 무 확인")
    @Test
    fun checkPlayerDraw() {
        val players = Players.from("ace,hi,con")
        val onlyPlayers = players.players.filterNot { it is Dealer }
        repeat(2) { players.players[0].addCard(Card(SuitType.CLUB, ValueType.NINE)) }
        repeat(2) { onlyPlayers.forEach { it.addCard(Card(SuitType.CLUB, ValueType.NINE)) } }
        players.calculateResult()

        val drawCount = onlyPlayers.count { it.playResult == PlayResultType.DRAW }

        assertThat(drawCount).isEqualTo(onlyPlayers.count())
    }
}
