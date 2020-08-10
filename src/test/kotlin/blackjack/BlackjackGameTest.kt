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

    @DisplayName("게임 진행여부 입력값 확인하기")
    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["a", "$"])
    fun checkInputHitOrStay(isHit: String) {
        val blackjackGame = BlackjackGame("ace,con", CardDeck())
        assertThatThrownBy { blackjackGame.hitOrStay(isHit) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @DisplayName("카드를 추가로 받기")
    @Test
    fun checkHit() {
        val blackjackGame = BlackjackGame("ace,con", CardDeck())
        blackjackGame.hitOrStay("y")
        assertThat(blackjackGame.currentPlayer.cards.size)
            .isEqualTo(3)
    }

    @DisplayName("카드를 추가로 안받기")
    @Test
    fun checkStay() {
        val blackjackGame = BlackjackGame("ace,con", CardDeck())
        blackjackGame.hitOrStay("n")
        assertThat(blackjackGame.currentPlayer.cards.size)
            .isEqualTo(2)
    }

    @DisplayName("게임 중 사용자 포인트 합 계산하기")
    @Test
    fun checkPointCalculation() {
        val blackjackGame = BlackjackGame("ace,con", CardDeck())
        assertThat(blackjackGame.currentPlayer.calculatePoint()).isGreaterThan(0)
    }

    @DisplayName("사용자가 현재 가지고 있는 카드 포인트의 합이 21이 넘는지 안넘는지 확인")
    @Test
    fun checkBust() {
        val blackjackGame = BlackjackGame("ace,con", CardDeck())
        repeat(10) { blackjackGame.hitOrStay("y") }
        assertThat(blackjackGame.currentPlayer.isBusted()).isEqualTo(true)
    }

    @DisplayName("다음턴 사용자 확인")
    @Test
    fun checkNextTurn() {
        val blackjackGame = BlackjackGame("ace,hi,con,race", CardDeck())
        repeat(4) { blackjackGame.hitOrStay("y") }
        assertThat(blackjackGame.currentPlayer.name).isEqualTo("ace")
    }
}
