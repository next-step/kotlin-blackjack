package blackjack

import blackjack.card.score.BlackJackScoringStrategy
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame(
    private val dealer: Dealer,
    private val blackJackScoringStrategy: BlackJackScoringStrategy,
) {
    fun start() {
        val playerNames = InputView.getPlayers()
        val players = playerNames.map { Player(it) }

        val initialCastingCardNum = 2
        dealer.provideCard(players, initialCastingCardNum)
        OutputView.printInitialCardCasting(players, initialCastingCardNum)

        players.forEach { attemptCasting(it) }
    }

    private fun attemptCasting(player: Player) {
        while (ableToCastMore(player) && InputView.askCastCardToPlayer(player)) {
            dealer.provideCard(listOf(player), 1)
            OutputView.printPlayerCards(player)
        }
    }

    private fun ableToCastMore(player: Player): Boolean {
        return blackJackScoringStrategy.score(player.cards) < BLACK_JACK_GOAL_NUMBER
    }

    companion object {
        private const val BLACK_JACK_GOAL_NUMBER = 21
    }
}
