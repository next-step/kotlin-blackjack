package blackjack

import blackjack.domain.BLACKJACK_POINT
import blackjack.domain.BetMoney
import blackjack.domain.BlackjackGame
import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.FIRST_PICK_COUNT
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
        val players = Players.from("ace,con")!!
        val blackjackGame = BlackjackGame(players)
        assertThat(blackjackGame.hitOrStay(isHit))
            .isNull()
    }

    @DisplayName("카드를 추가로 받기")
    @Test
    fun checkHit() {
        val players = Players.from("ace,con")
        val blackjackGame = BlackjackGame(players)
        blackjackGame.startGame()
        blackjackGame.hitOrStay(HIT)

        assertThat(blackjackGame.players.currentPlayer.cards.size)
            .isEqualTo(3)
    }

    @DisplayName("카드를 추가로 안받기")
    @Test
    fun checkStay() {
        val players = Players.from("ace,con")
        val blackjackGame = BlackjackGame(players)
        blackjackGame.hitOrStay(STAY)
        blackjackGame.startGame()

        val cardCount = blackjackGame.players.currentPlayer.cards.size

        assertThat(cardCount)
            .isEqualTo(FIRST_PICK_COUNT)
    }

    @DisplayName("게임 중 사용자 포인트 합 계산하기")
    @Test
    fun checkPointCalculation() {
        val players = Players.from("ace,con")
        val blackjackGame = BlackjackGame(players)
        blackjackGame.startGame()

        val actual = blackjackGame.players.currentPlayer.point

        assertThat(actual).isGreaterThan(0).isLessThanOrEqualTo(BLACKJACK_POINT)
    }

    @DisplayName("사용자가 현재 가지고 있는 카드 포인트의 합이 21이 넘는지 안넘는지 확인")
    @Test
    fun checkBust() {
        val players = Players.from("ace,con")
        val blackjackGame = BlackjackGame(players)
        repeat(10) { blackjackGame.hitOrStay(HIT) }
        assertThat(blackjackGame.players.currentPlayer.isBusted()).isEqualTo(true)
    }

    @DisplayName("다음턴 사용자 확인")
    @Test
    fun checkNextPlayer() {
        val players = Players.from("ace,hi,con,race")
        val blackjackGame = BlackjackGame(players)
        assertThat(blackjackGame.players.getNextPlayer()?.name).isEqualTo("hi")
    }

    @DisplayName("다음턴 사용자 중 버스트된 사용자 있으면 턴에 포함되지 않게 하는지 확인")
    @Test
    fun checkNextPlayerWithBusted() {
        val players = Players.from("ace,hi")
        val blackjackGame = BlackjackGame(players)
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

    private val betMoneyMap: BetMoneyMap = BetMoneyMap(
        mapOf(
            "ace" to BetMoney.of("1000")!!,
            "hi" to BetMoney.of("2000")!!,
            "con" to BetMoney.of("3000")!!
        )
    )

    @DisplayName("플레이어가 승리했을 경우 수익금 확인")
    @Test
    fun checkProfitMoneyWin() {
        val players = Players.from("ace,hi,con")

        val blackjackGame = BlackjackGame(players, betMoneyMap)
        val onlyPlayers = blackjackGame.players.players.filterNot { it is Dealer }

        repeat(3) { players.players[0].addCard(Card(SuitType.CLUB, ValueType.EIGHT)) }
        repeat(2) { onlyPlayers.forEach { it.addCard(Card(SuitType.CLUB, ValueType.K)) } }
        players.calculateResult()

        val totalProfitMoney = onlyPlayers.sumBy { blackjackGame.getPlayerProfitMoney(it) }

        assertThat(totalProfitMoney).isEqualTo(6000)
    }

    @DisplayName("플레이어가 패배했을 경우 수익금 확인")
    @Test
    fun checkProfitMoneyLose() {
        val players = Players.from("ace,hi,con")
        val blackjackGame = BlackjackGame(players, betMoneyMap)
        val onlyPlayers = blackjackGame.players.players.filterNot { it is Dealer }

        repeat(2) { players.players[0].addCard(Card(SuitType.CLUB, ValueType.NINE)) }
        repeat(3) { onlyPlayers.forEach { it.addCard(Card(SuitType.CLUB, ValueType.K)) } }
        players.calculateResult()

        val totalProfitMoney = onlyPlayers.sumBy { blackjackGame.getPlayerProfitMoney(it) }
        assertThat(totalProfitMoney).isEqualTo(-6000)
    }

    @DisplayName("플레이어와 딜러가 무승부일 경우 수익금 확인")
    @Test
    fun checkProfitMoneyDraw() {
        val players = Players.from("ace,hi,con")
        val blackjackGame = BlackjackGame(players, betMoneyMap)
        val onlyPlayers = blackjackGame.players.players.filterNot { it is Dealer }

        players.players.forEach {
            it.addCard(Card(SuitType.CLUB, ValueType.Q))
            it.addCard(Card(SuitType.CLUB, ValueType.A))
        }
        players.calculateResult()
        val totalProfitMoney = onlyPlayers.sumBy { blackjackGame.getPlayerProfitMoney(it) }

        assertThat(totalProfitMoney).isEqualTo(0)
    }

    @DisplayName("플레이어가 블랙잭으로 승리했을 경우 수익금 확인")
    @Test
    fun checkProfitMoneyWithBlackJack() {
        val players = Players.from("ace,hi,con")
        val blackjackGame = BlackjackGame(players, betMoneyMap)
        val onlyPlayers = blackjackGame.players.players.filterNot { it is Dealer }

        players.players.forEach {
            it.addCard(Card(SuitType.CLUB, ValueType.Q))
            it.addCard(Card(SuitType.CLUB, ValueType.A))
        }
        players.calculateResult(20)
        val totalProfitMoney = onlyPlayers.sumBy { blackjackGame.getPlayerProfitMoney(it) }

        assertThat(totalProfitMoney).isEqualTo(9000)
    }
}
