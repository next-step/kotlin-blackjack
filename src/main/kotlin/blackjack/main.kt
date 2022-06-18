package blackjack

import blackjack.application.BlackJack
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.ui.input.InputView
import blackjack.ui.output.PlayingView
import blackjack.ui.output.ResultView
import blackjack.domain.participant.BlackJack as BlackjackState

fun main() {
    val dealer = Dealer()
    val players = participate()

    val blackjack = BlackJack(dealer, players)

    blackjack.distribute()
    PlayingView.showDistribution(dealer, players)

    if (dealer.state !is BlackjackState) {
        blackjack.confirmBlackJackPlayer()
        val remainedPlayers = players.filterNot { it.state is BlackjackState }
        deal(blackjack, dealer, remainedPlayers)
    }
    ResultView.showResultHand(listOf(dealer) + players)

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
