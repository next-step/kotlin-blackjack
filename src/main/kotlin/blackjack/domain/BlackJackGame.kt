package blackjack.domain

import blackjack.domain.user.Dealer
import blackjack.domain.user.PlayerGroup
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackGame(private val inputView: InputView, private val resultView: ResultView) {

    private lateinit var playerGroup: PlayerGroup
    private val dealer: Dealer = Dealer()

    fun play() {
        initGame()
        drawCards()
        printGameResult()
    }

    private fun initGame() {
        val playerNames: List<String> = inputView.getPlayerNames()
        playerGroup = PlayerGroup(playerNames)

        playerGroup.players.forEach {
            player ->
            dealer.giveCardTo(player, 2)
            resultView.printPlayerCards(player)
        }
    }

    private fun drawCards() {
        playerGroup.players.forEach { player ->
            generateSequence {
                player.chooseHitOrStay(inputView.getPlayerHitStatus(player.name))
                dealer.giveCardIfPlayerWantHit(player)
                player.updateStatus()

                player
            }.takeWhile { !it.isDone() }
                .forEach { resultView.printPlayerCards(it) }
        }
    }

    private fun printGameResult() {
        playerGroup.players.forEach {
            player ->
            resultView.printResult(player)
        }
    }
}
