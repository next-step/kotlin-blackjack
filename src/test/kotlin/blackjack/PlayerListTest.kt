package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.suit.SuitTypes.Diamond
import blackjack.domain.player.Bet
import blackjack.domain.player.Dealer
import blackjack.domain.player.Gamer
import blackjack.domain.player.PlayerList
import blackjack.domain.player.PlayerName
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerListTest {
    @Test
    fun `딜러가 blackjack 일 경우의 게임 결과 값 들을 받아 올 수 있다`() {
        val betValue = 1000
        val blackjackGamerWith2card = makeGamer("blackjackWith2CardGamer", listOf(1, 10), betValue)
        val blackjackGamer = makeGamer("blackjackGamer", listOf(1, 1, 9), betValue)
        val bustedGamer = makeGamer("bustedGamer", listOf(2, 10, 10), betValue)
        val losedGamer = makeGamer("losedGamer", listOf(1, 2), betValue)
        val blackjackDealer = makeDealer("Dealer", listOf(1, 10))

        val playerList =
            PlayerList(mutableListOf(blackjackGamerWith2card, blackjackGamer, losedGamer, bustedGamer), blackjackDealer)
        val gameResults = playerList.getGameResultList()
        val actualResultOfBlackjackWith2Card =
            gameResults.find { it.playerStatus.name == PlayerName("blackjackWith2CardGamer") }!!
        assertThat(actualResultOfBlackjackWith2Card.betResult.value).isEqualTo(1500)

        val actualResultOfBlackjackPlayer =
            gameResults.find { it.playerStatus.name == PlayerName("blackjackGamer") }!!
        assertThat(actualResultOfBlackjackPlayer.betResult.value).isEqualTo(0)

        val actualResultOfBustedPlayer =
            gameResults.find { it.playerStatus.name == PlayerName("bustedGamer") }!!
        assertThat(actualResultOfBustedPlayer.betResult.value).isEqualTo(-1000)

        val actualResultOfLosedGamer =
            gameResults.find { it.playerStatus.name == PlayerName("losedGamer") }!!
        assertThat(actualResultOfLosedGamer.betResult.value).isEqualTo(-1000)
    }

    @Test
    fun `딜러의 handValue가 blackjack 미만일 경우의 게임 결과 값 들을 받아 올 수 있다`() {
        val betValue = 1000
        val blackjackGamerWith2card = makeGamer("blackjackWith2CardGamer", listOf(1, 10), betValue)
        val blackjackGamer = makeGamer("blackjackGamer", listOf(1, 1, 9), betValue)
        val winGamer = makeGamer("winGamer", listOf(1, 9), betValue)
        val bustedGamer = makeGamer("bustedGamer", listOf(2, 10, 10), betValue)
        val losedGamer = makeGamer("losedGamer", listOf(1, 2), betValue)
        val blackjackDealer = makeDealer("Dealer", listOf(10, 7))

        val playerList =
            PlayerList(
                mutableListOf(blackjackGamerWith2card, blackjackGamer, winGamer, losedGamer, bustedGamer),
                blackjackDealer
            )
        val gameResults = playerList.getGameResultList()
        val actualResultOfBlackjackWith2Card =
            gameResults.find { it.playerStatus.name == PlayerName("blackjackWith2CardGamer") }!!
        assertThat(actualResultOfBlackjackWith2Card.betResult.value).isEqualTo(2500)

        val actualResultOfBlackjackPlayer =
            gameResults.find { it.playerStatus.name == PlayerName("blackjackGamer") }!!
        assertThat(actualResultOfBlackjackPlayer.betResult.value).isEqualTo(1000)

        val actualResultOfBustedPlayer =
            gameResults.find { it.playerStatus.name == PlayerName("bustedGamer") }!!
        assertThat(actualResultOfBustedPlayer.betResult.value).isEqualTo(-1000)

        val actualResultOfWinGamer =
            gameResults.find { it.playerStatus.name == PlayerName("winGamer") }!!
        assertThat(actualResultOfWinGamer.betResult.value).isEqualTo(1000)

        val actualResultOfLosedGamer =
            gameResults.find { it.playerStatus.name == PlayerName("losedGamer") }!!
        assertThat(actualResultOfLosedGamer.betResult.value).isEqualTo(-1000)
    }

    @Test
    fun `딜러가 busted될 경우의 게임 결과 값 들을 받아 올 수 있다`() {
        val betValue = 1000
        val blackjackGamerWith2card = makeGamer("blackjackWith2CardGamer", listOf(1, 10), betValue)
        val blackjackGamer = makeGamer("blackjackGamer", listOf(1, 1, 9), betValue)
        val bustedGamer = makeGamer("bustedGamer", listOf(2, 10, 10), betValue)
        val losedGamer = makeGamer("losedGamer", listOf(1, 2), betValue)
        val blackjackDealer = makeDealer("Dealer", listOf(1, 10, 10, 10))

        val playerList =
            PlayerList(mutableListOf(blackjackGamerWith2card, blackjackGamer, losedGamer, bustedGamer), blackjackDealer)
        val gameResults = playerList.getGameResultList()
        val actualResultOfBlackjackWith2Card =
            gameResults.find { it.playerStatus.name == PlayerName("blackjackWith2CardGamer") }!!
        assertThat(actualResultOfBlackjackWith2Card.betResult.value).isEqualTo(2500)

        val actualResultOfBlackjackPlayer =
            gameResults.find { it.playerStatus.name == PlayerName("blackjackGamer") }!!
        assertThat(actualResultOfBlackjackPlayer.betResult.value).isEqualTo(1000)

        val actualResultOfBustedPlayer =
            gameResults.find { it.playerStatus.name == PlayerName("bustedGamer") }!!
        assertThat(actualResultOfBustedPlayer.betResult.value).isEqualTo(1000)

        val actualResultOfLosedGamer =
            gameResults.find { it.playerStatus.name == PlayerName("losedGamer") }!!
        assertThat(actualResultOfLosedGamer.betResult.value).isEqualTo(1000)
    }

    fun makeGamer(name: String, listOfCardNumber: List<Int>, betValue: Int) = Gamer(
        PlayerName(name),
        Hand(
            listOfCardNumber
                .map { Card(Diamond, it) }
                .toMutableList()
        ),
        Bet(betValue)
    )

    fun makeDealer(name: String, listOfCardNumber: List<Int>) = Dealer(
        PlayerName(name),
        Hand(
            listOfCardNumber
                .map { Card(Diamond, it) }
                .toMutableList()
        )
    )
}
