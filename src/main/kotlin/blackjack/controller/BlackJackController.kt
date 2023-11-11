package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.TrumpCard
import blackjack.view.BlackJackDealerResult
import blackjack.view.BlackJackPlayerResult
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {

    fun start() {
        val trumpCard = TrumpCard.init()
        val playerNames = InputView.inputPlayerName()
        val players = playerNames.map {
            Player(it, trumpCard.drawFirstCards())
        }
        val dealer = Dealer(trumpCard.drawFirstCards())
        OutputView.printInitCard(dealer.result() to players.map { it.result() })
        val blackJackResult = playBlackjack(players, dealer, trumpCard)
        OutputView.printBlackjackGameResult(blackJackResult)
    }

    private fun playBlackjack(
        players: List<Player>,
        dealer: Dealer,
        trumpCard: TrumpCard
    ): Pair<BlackJackDealerResult, List<BlackJackPlayerResult>> {
        players.draw(trumpCard)
        dealer.draw(trumpCard)
        return dealer.result() to players.map { it.result() }
    }

    private fun Player.result(): BlackJackPlayerResult {
        return BlackJackPlayerResult(this)
    }

    private fun Dealer.result(): BlackJackDealerResult {
        return BlackJackDealerResult(this)
    }

    private fun List<Player>.draw(trumpCard: TrumpCard) {
        this.forEach {
            while (it.isHit() && InputView.inputHitOrStand(it.name)) {
                it.drawBy(trumpCard)
                OutputView.printPlayerCard(it.result())
            }
            if (it.isBurst()) OutputView.printPlayerBurst(it.name)
        }
    }

    private fun Dealer.draw(trumpCard: TrumpCard) {
        while (this.isHit()) {
            OutputView.printDealerDraw()
            this.drawBy(trumpCard)
        }
    }
}
