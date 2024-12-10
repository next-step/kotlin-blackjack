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
        players = inputView.inputPlayerNames()
        resultView.printInitGiveCardsMessage(players, INIT_CARD_COUNT)

        startGame()
        players.forEach { player ->
            playGame(player)
        }

        addCardDealer()
        finishGame()
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

    private fun addCardDealer() {
        if (dealer.cards().calculateScore() <= DEALER_SCORE) {
            resultView.printAddCardOfDealerMessage()
            blackjackOperator.addCard(dealer)
        }
    }

    private fun playGame(player: BlackjackUser) {
        if (Match.bustCheck(player.cards().calculateScore())) {
            return
        }

        while (inputView.inputGiveCardMessage(player)) {
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
        private const val DEALER_SCORE = 16
    }
}

fun main() {
    BlackjackGame().run()
}
