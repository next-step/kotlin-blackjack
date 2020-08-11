package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.domain.HIT
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
        assertThat(player.point).isEqualTo(10)
        assertThat(player.calculatePoint(true)).isEqualTo(20)
    }

    @DisplayName("다음턴 사용자 확인")
    @Test
    fun checkNextTurn() {
        val blackjackGame = BlackjackGame("ace,hi,con,race", CardDeck())
        repeat(4) { blackjackGame.hitOrStay(HIT) }
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

    @DisplayName("딜러의 hit 여부")
    @Test
    fun checkHitForDealer() {
        val dealer = Dealer()
        dealer.addCard(Card(SuitType.CLUB, ValueType.J))
        dealer.addCard(Card(SuitType.CLUB, ValueType.K))

        val isHit = dealer.isHit

        assertThat(isHit).isEqualTo(false)
    }

    @DisplayName("A가 포함되어 있을 경우 딜러의 계산방식, A =11 일경우 21 미만")
    @Test
    fun checkDealerPointWithAce() {
        val dealer = Dealer()
        dealer.addCard(Card(SuitType.CLUB, ValueType.FOUR))
        dealer.addCard(Card(SuitType.CLUB, ValueType.FIVE))
        dealer.addCard(Card(SuitType.CLUB, ValueType.A))

        val point = dealer.point

        assertThat(point).isEqualTo(20)
    }

    @DisplayName("A가 포함되어 있을 경우 딜러의 계산방식, A =11 일경우 21 초과")
    @Test
    fun checkDealerPointWithAceMoreThanBlackjackPoint() {
        val dealer = Dealer()
        dealer.addCard(Card(SuitType.CLUB, ValueType.FOUR))
        dealer.addCard(Card(SuitType.CLUB, ValueType.SIX))
        dealer.addCard(Card(SuitType.CLUB, ValueType.K))
        dealer.addCard(Card(SuitType.CLUB, ValueType.A))

        val point = dealer.point

        assertThat(point).isEqualTo(21)
    }
}
