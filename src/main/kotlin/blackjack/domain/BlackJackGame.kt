package blackjack.domain

import blackjack.domain.user.Dealer
import blackjack.domain.user.Player
import blackjack.domain.user.PlayerGroup
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackGame(private val inputView: InputView, private val resultView: ResultView) {

    private lateinit var playerGroup: PlayerGroup
    private val dealer: Dealer = Dealer()

    fun play() {
        initGame()
        drawCardsInTurn()
        printGameResult()
    }

    private fun initGame() {
        val playerNames: List<String> = inputView.getPlayerNames()
        playerGroup = PlayerGroup.create(playerNames)

        playerGroup.players.forEach {
            player ->
            dealer.giveCardTo(player, 2)
            resultView.printPlayerCards(player)
        }
    }

    private fun drawCardsInTurn() = playerGroup.players.forEach(::drawCards)

    private fun drawCards(player: Player) {
        while (!player.isDone()) {
            player.chooseHitOrStay(inputView.getIsPlayerWantHit(player.name))
            dealer.giveCardIfPlayerWantHit(player)
        }
        resultView.printPlayerCards(player)
    }

    private fun printGameResult() {
        playerGroup.players.forEach {
            player ->
            resultView.printResult(player)
        }
    }
}
