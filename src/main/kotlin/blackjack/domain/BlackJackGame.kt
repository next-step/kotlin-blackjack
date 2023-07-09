package blackjack.domain

import blackjack.domain.status.PlayingStatus
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
            player.draw(dealer.deck.getNextCard(), 2)
            resultView.printPlayerCards(player)
        }

        dealer.draw(dealer.deck.getNextCard())
        resultView.printPlayerCards(dealer)
    }

    private fun drawCardsForEachPlayer() = playerGroup.players.forEach(::drawCards)

    private fun drawCards(player: Player) {
        while (!player.isDone()) {
            player.chooseHitOrStay(inputView.getIsPlayerWantHit(player.name), dealer)
            resultView.printPlayerCards(player)
        }
    }

    private fun drawCardsForDealer() {
        while (dealer.status is PlayingStatus) {
            dealer.draw(dealer.deck.getNextCard())
            resultView.printDealerDrawCardAlert(16)
        }
        dealer.judgeResult(playerGroup)
    }


    private fun printGameResult() {
        resultView.printResult(dealer)
        playerGroup.players.forEach {
            player ->
            resultView.printResult(player)
        }

        resultView.printFinalResult(dealer)
        playerGroup.players.forEach {
                player ->
            resultView.printFinalResult(player)
        }
    }

}
