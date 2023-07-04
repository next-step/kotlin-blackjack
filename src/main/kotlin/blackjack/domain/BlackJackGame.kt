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
        drawCardsForEachPlayer()
        drawCardsForDealer()
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

        dealer.giveCardTo(dealer, 2)
        resultView.printPlayerCards(dealer)
    }

    private fun drawCardsForEachPlayer() = playerGroup.players.forEach(::drawCards)

    private fun drawCards(player: Player) {
        generateSequence {
            player.chooseHitOrStay(inputView.getIsPlayerWantHit(player.name))
            dealer.giveCardIfPlayerWantHit(player)
            player
        }.takeWhile { !it.isDone() }
            .forEach { resultView.printPlayerCards(it) }
    }

    private fun drawCardsForDealer() {
        while (dealer.drawCardBySelfIfPointUnder(DEALER_DRAW_THRESHOLD_POINT)) {
            resultView.printDealerDrawCardAlert(DEALER_DRAW_THRESHOLD_POINT)
        }
    }


    private fun printGameResult() {
        playerGroup.players.forEach {
            player ->
            resultView.printResult(player)
        }
    }

    companion object {
        private const val DEALER_DRAW_THRESHOLD_POINT = 16
    }
}
