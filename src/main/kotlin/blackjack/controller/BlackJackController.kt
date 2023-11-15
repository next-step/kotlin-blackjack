package blackjack.controller

import blackjack.domain.BlackJackDealerResult
import blackjack.domain.BlackJackPlayerResult
import blackjack.domain.Dealer
import blackjack.domain.Money
import blackjack.domain.Player
import blackjack.domain.TrumpCard
import blackjack.domain.WinLose
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {

    fun start() {
        val trumpCard = TrumpCard.init()
        val playerNames = InputView.inputPlayerName()
        val players = playerNames.map {
            Player(it, trumpCard.drawFirstCards(), InputView.inputBetMoney(it))
        }
        OutputView.printPlayerName(playerNames)
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
        val result = players.map { it.match(dealer) to it.winLoseMoney(dealer) }
            .groupBy { it.first }
            .mapValues { Money(it.value.sumOf { money -> money.second.amount }) }

        return dealer.result(result) to players.map { it.result(it.winLoseMoney(dealer)) }
    }

    private fun Player.result(money: Money = Money()): BlackJackPlayerResult {
        return BlackJackPlayerResult(this, money)
    }

    private fun Dealer.result(winLoseMap: Map<WinLose, Money> = mapOf()): BlackJackDealerResult {
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
                return@forEach
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
