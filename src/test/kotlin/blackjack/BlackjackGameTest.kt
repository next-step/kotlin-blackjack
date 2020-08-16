package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.HIT
import blackjack.domain.Players
import blackjack.domain.STAY
import blackjack.domain.SuitType
import blackjack.domain.ValueType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class BlackjackGameTest {

    @DisplayName("게임 진행여부 입력값 확인하기")
    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["a", "$"])
    fun checkInputHitOrStay(isHit: String) {
        val players = Players.newInstance("ace,con")
        val blackjackGame = BlackjackGame(players!!, CardDeck())
        assertThat(blackjackGame.hitOrStay(isHit))
            .isNull()
    }

    @DisplayName("카드를 추가로 받기")
    @Test
    fun checkHit() {
        val players = Players.newInstance("ace,con")
        val blackjackGame = BlackjackGame(players!!, CardDeck())
        blackjackGame.hitOrStay(HIT)
        assertThat(blackjackGame.players.currentPlayer.cards.size)
            .isEqualTo(3)
    }

    @DisplayName("카드를 추가로 안받기")
    @Test
    fun checkStay() {
        val players = Players.newInstance("ace,con")
        val blackjackGame = BlackjackGame(players!!, CardDeck())
        blackjackGame.hitOrStay(STAY)
        assertThat(blackjackGame.players.currentPlayer.cards.size)
            .isEqualTo(2)
    }

    @DisplayName("게임 중 사용자 포인트 합 계산하기")
    @Test
    fun checkPointCalculation() {
        val players = Players.newInstance("ace,con")
        val blackjackGame = BlackjackGame(players!!, CardDeck())
        assertThat(blackjackGame.players.currentPlayer.point).isGreaterThan(0)
    }

    @DisplayName("사용자가 현재 가지고 있는 카드 포인트의 합이 21이 넘는지 안넘는지 확인")
    @Test
    fun checkBust() {
        val players = Players.newInstance("ace,con")
        val blackjackGame = BlackjackGame(players!!, CardDeck())
        repeat(10) { blackjackGame.hitOrStay(HIT) }
        assertThat(blackjackGame.players.currentPlayer.isBusted()).isEqualTo(true)
    }

    @DisplayName("다음턴 사용자 확인")
    @Test
    fun checkNextPlayer() {
        val players = Players.newInstance("ace,hi,con,race")
        val blackjackGame = BlackjackGame(players!!, CardDeck())
        assertThat(blackjackGame.players.getNextPlayer()?.name).isEqualTo("hi")
    }

    @DisplayName("다음턴 사용자 중 버스트된 사용자 있으면 턴에 포함되지 않게 하는지 확인")
    @Test
    fun checkNextPlayerWithBusted() {
        val players = Players.newInstance("ace,hi")
        val blackjackGame = BlackjackGame(players!!, CardDeck())
        repeat(3) { players.players[1].addCard(Card(SuitType.CLUB, ValueType.NINE)) }

        blackjackGame.nextTurn()
        assertThat(players.currentPlayer)
            .isNotEqualTo(players.players[1])
        blackjackGame.nextTurn()
        assertThat(players.currentPlayer)
            .isNotEqualTo(players.players[1])
        blackjackGame.nextTurn()
        assertThat(players.currentPlayer)
            .isNotEqualTo(players.players[1])
    }
}
