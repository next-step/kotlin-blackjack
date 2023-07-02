package blackjack.application

import blackjack.BlackjackShoe
import blackjack.Player
import blackjack.application.view.InputView
import blackjack.application.view.OutputView

class BlackjackApplication(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val blackjackShoe: BlackjackShoe = BlackjackShoe(),
) {

    fun run() {
        outputView.showInputPlayerNames()
        val playerNameList = inputView.getPlayers()

        val playerList = playerNameList.split(",")
            .map { it.trim() }
            .map { Player(it) }

        playerList.forEach { player ->
            repeat(2) {
                val card = blackjackShoe.draw()
                player.receiveCard(card)
            }
        }
        outputView.showDefaultDrawToPlayers(playerList)

        playerList.forEach { player ->
            while (player.canReceiveCard) {
                outputView.showDoYouWantCard(player)
                if (inputView.getMoreCard()) {
                    val card = blackjackShoe.draw()
                    player.receiveCard(card)
                    outputView.showPlayerCardList(player)
                } else {
                    break
                }
            }
        }

        outputView.showPlayersInfo(playerList)
    }

}

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val blackjackApplication = BlackjackApplication(inputView, outputView)
    blackjackApplication.run()
}
