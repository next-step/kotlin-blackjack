package blackjack.controller

import blackjack.domain.BlackJackDealerResult
import blackjack.domain.BlackJackPlayerResult
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.TrumpCard
import blackjack.domain.WinLose
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {

    fun start() {
        val trumpCard = TrumpCard.init()
        val playerNames = InputView.inputPlayerName()
        OutputView.printPlayerName(playerNames)
        val players = playerNames.map {
            Player(it, trumpCard.drawFirstCards())
        }
        val dealer = Dealer(trumpCard.drawFirstCards())
        OutputView.printFirstCard(dealer.result())
        OutputView.printPlayerFirstCard(players.map { it.result() })
        val (dealerResult, playersResults) = playBlackjack(players, dealer, trumpCard)
        OutputView.printBlackjackGameResult(dealerResult, playersResults)
    }

    private fun playBlackjack(
        players: List<Player>,
        dealer: Dealer,
        trumpCard: TrumpCard
    ): Pair<BlackJackDealerResult, List<BlackJackPlayerResult>> {
        players.draw(trumpCard)
        dealer.draw(trumpCard)
        val dealerWinLose = players.map { it.match(dealer).opposite() }
            .groupingBy { it }
            .eachCount()

        return dealer.result(dealerWinLose) to players.map { it.result(it.match(dealer)) }
    }

    private fun Player.result(winLose: WinLose = WinLose.NONE): BlackJackPlayerResult {
        return BlackJackPlayerResult(this, winLose)
    }

    private fun Dealer.result(winLoseMap: Map<WinLose, Int> = mapOf()): BlackJackDealerResult {
        return BlackJackDealerResult(this, winLoseMap)
    }

    private fun List<Player>.draw(trumpCard: TrumpCard) {
        this.forEach {
            while (it.isHit() && InputView.inputHitOrStand(it.name)) {
                it.drawBy(trumpCard.draw())
                OutputView.printPlayerCard(it.result())
            }
            if (it.isBurst()) {
                OutputView.printPlayerBurst(it.name)
            }
            it.stand()
        }
    }

    private fun Dealer.draw(trumpCard: TrumpCard) {
        while (this.isHit()) {
            OutputView.printDealerDraw()
            this.drawBy(trumpCard.draw())
        }
    }
}
