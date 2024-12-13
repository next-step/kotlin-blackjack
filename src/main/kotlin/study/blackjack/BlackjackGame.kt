package study.blackjack

import study.blackjack.model.BlackjackUser
import study.blackjack.model.Match
import study.blackjack.view.InputView
import study.blackjack.view.ResultView

/**
 * @author 이상준
 */
class BlackjackGame(
    private var players: List<BlackjackUser> = listOf(),
    private val blackjackOperator: BlackjackOperator = BlackjackOperator(),
    private var dealer: BlackjackUser = BlackjackUser(DEALER),
) {
    private val inputView = InputView()
    private val resultView = ResultView()

    fun run() {
        initGameSettings()
        startGame()
        drawPlayer()
        drawDealer()
        finishGame()
    }

    private fun initGameSettings() {
        players = inputView.inputPlayerNames()
        players.forEach {
            inputView.inputPlayerMoney(it)
        }
        resultView.printInitGiveCardsMessage(players, INIT_CARD_COUNT)
    }

    private fun startGame() {
        blackjackOperator.addCard(dealer)
        resultView.printInitCardOfPlayer(dealer)
        blackjackOperator.addCard(dealer)

        players.forEach { player ->
            repeat(INIT_CARD_COUNT) {
                blackjackOperator.addCard(player)
            }

            resultView.printInitCardOfPlayer(player)
        }
    }

    private fun drawPlayer() {
        players.forEach { player ->
            drawPlayer(player)
        }
    }

    private fun drawDealer() {
        if (dealer.isReceiveCard()) {
            resultView.printAddCardOfDealerMessage()
            blackjackOperator.addCard(dealer)
        }
    }

    private fun drawPlayer(player: BlackjackUser) {
        while (Match.bustCheck(player.cards().calculateScore()).not() && inputView.inputGiveCardMessage(player)) {
            blackjackOperator.addCard(player)
            resultView.printInitCardOfPlayer(player)
        }
    }

    private fun finishGame() {
        resultView.printResultCardOfPlayer(dealer)
        players.forEach { player ->
            resultView.printResultCardOfPlayer(player)
            player.match(dealer)
        }

        resultView.printFinalStats(players)
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
        private const val DEALER = "딜러"
    }
}

fun main() {
    BlackjackGame().run()
}
