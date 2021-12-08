package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.suit.SuitTypes.Diamond
import blackjack.domain.player.Bet
import blackjack.domain.player.Gamer
import blackjack.domain.player.PlayerName
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GamerTest {
    @ParameterizedTest
    @ValueSource(strings = ["player1", "player2", "player3"])
    fun `참가자에게 이름을 적용 할 수 있다`(predictedName: String) {
        val predictedPlayerName = predictedName.toPlayerName()
        val gamer = Gamer(predictedPlayerName, bet = Bet(0))
        val actualName = gamer.getName()
        assertThat(actualName).isEqualTo(predictedPlayerName)
    }

    @Test
    fun `참가자가 가지고 있는 카드 목록을 가져 올 수 있다`() {
        val gamer = Gamer("gamer".toPlayerName(), bet = Bet(0))
        with(gamer) {
            addCardToHand(Card(Diamond, 1))
            addCardToHand(Card(Diamond, 9))
        }
        val actualResult = gamer.getCards()
        assertThat(actualResult).contains(Card(Diamond, 1), Card(Diamond, 9))
    }

    @Test
    fun `참가자가 가지고 있는 카드값 합들을 가져 올 수 있다`() {
        val hand = Hand().apply {
            addCardToHand(Card(Diamond, 1))
            addCardToHand(Card(Diamond, 9))
        }
        val gamer = Gamer("gamer".toPlayerName(), hand, Bet(0))
        assertThat(gamer.getMakeableValues()).isEqualTo(listOf(20, 10))
        gamer.addCardToHand(Card(Diamond, 1))
        assertThat(gamer.getMakeableValues()).isEqualTo(listOf(21))
    }

    @Test
    fun `참가자는 hand의 값이 21 미만일 시 추가 카드를 hand에 추가 할 수 있다`() {

        val hand = Hand().apply {
            addCardToHand(Card(Diamond, 5))
            addCardToHand(Card(Diamond, 6))
        }
        val gamer = Gamer("gamer".toPlayerName(), hand, Bet(0))
        val actualResult = gamer.isHandAddable()
        assertThat(actualResult).isTrue
    }

    @Test
    fun `참가자는 hand의 값이 21 이상일 시 추가 카드를 hand에 추가 할 수 없다`() {
        val hand = Hand().apply {
            addCardToHand(Card(Diamond, 5))
            addCardToHand(Card(Diamond, 6))
            addCardToHand(Card(Diamond, 10))
        }
        val gamer = Gamer("gamer".toPlayerName(), hand, Bet(0))
        val actualResult = gamer.isHandAddable()
        assertThat(actualResult).isFalse
    }

    @Test
    fun `참가자는 딜러의 결과를 통해서 자신의 승패를 확인 할 수 있다`() {
        val dealerValue = 18
        val winHand = Hand().apply {
            addCardToHand(Card(Diamond, 5))
            addCardToHand(Card(Diamond, 6))
            addCardToHand(Card(Diamond, 10))
        }
        val winPlayer = Gamer("gamer".toPlayerName(), winHand, Bet(0))
        winPlayer.setResultByDealerScore(dealerValue, false)
        val actualWinResult = winPlayer.getWins()
        assertThat(actualWinResult).isEqualTo(1)

        val loseHand = Hand().apply {
            addCardToHand(Card(Diamond, 5))
            addCardToHand(Card(Diamond, 6))
        }
        val losePlayer = Gamer("gamer".toPlayerName(), loseHand, Bet(0))
        losePlayer.setResultByDealerScore(dealerValue, false)
        val actualLoseResult = losePlayer.getLoses()
        assertThat(actualLoseResult).isEqualTo(1)

        loseHand.addCardToHand(Card(Diamond, 7))
        loseHand.addCardToHand(Card(Diamond, 8))

        val bustedPlayer = Gamer("gamer".toPlayerName(), loseHand, Bet(0))
        bustedPlayer.setResultByDealerScore(dealerValue, false)
        val actualBustedLoseResult = bustedPlayer.getLoses()
        assertThat(actualBustedLoseResult).isEqualTo(1)
    }

    fun String.toPlayerName() = PlayerName(this)
}
