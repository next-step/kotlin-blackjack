package blackjack

import blackjack.domain.BetMoney
import blackjack.domain.BlackjackGame
import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.HIT
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.SuitType
import blackjack.domain.ValueType
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
        val players = Players.newInstance(playerNames)
        assertThat(players)
            .isNull()
    }

    @DisplayName("플레이어 수 확인")
    @Test
    fun checkPlayers() {
        val players = Players.newInstance("ace,con")
        assertThat(BlackjackGame(players!!, CardDeck()).players.players.size)
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
        val players = Players.newInstance("ace,hi,con,race")
        val blackjackGame = BlackjackGame(players!!, CardDeck())
        repeat(4) { blackjackGame.hitOrStay(HIT) }
        assertThat(blackjackGame.players.currentPlayer.name).isEqualTo("ace")
    }

    @DisplayName("사용자가 카드 뽑고 나서 카드수")
    @Test
    fun checkPlayerCard() {
        val cardDeck = CardDeck()
        val players = Players.newInstance("ace,hi,con,race")
        val blackjackGame = BlackjackGame(players!!, cardDeck)
        blackjackGame.players.currentPlayerPickCard(true, cardDeck)
        assertThat(blackjackGame.players.currentPlayer.cards.size)
            .isEqualTo(3)
    }

    @DisplayName("사용자가 베팅머니 확인")
    @Test
    fun checkBetMoney() {
        val players = Players.newInstance("ace,hi,con,race")

        players!!.players.forEach {
            it.betMoney = BetMoney.newInstance("1000")
        }

        assertThat(players.players[0].betMoney!!.money)
            .isEqualTo(1000)
    }
}
