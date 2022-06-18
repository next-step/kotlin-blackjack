package blackjack

import blackjack.application.BlackJack
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.ui.input.InputView
import blackjack.ui.output.PlayingView
import blackjack.ui.output.ResultView

fun main() {
    val dealer = Dealer()
    val participants = participate()

    val blackjack = BlackJack(dealer, participants)

    blackjack.distribute()
    PlayingView.showDistribution(dealer, participants)

    deal(blackjack, dealer, participants)

    ResultView.showResultHand(listOf(dealer) + participants)

    val result = blackjack.matching()

    ResultView.showBlackJackResult(result)
}

private fun deal(blackJack: BlackJack, dealer: Dealer, players: List<Player>) {
    players.forEach { player ->
        blackJack.dealWith(player, InputView::askHit, PlayingView::showOpenHand)
    }
    blackJack.dealWith(dealer, { true }, PlayingView::showDealerHit)
}

private fun participate(): List<Player> {
    return InputView.readPlayerNames()
        .map { name ->
            Player(name, bettingMoney = InputView.getBettingMoney(name))
        }
}
