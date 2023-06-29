package blackjack

import blackjack.card.score.BlackJackScoringStrategy
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame(
    private val dealer: Dealer,
    private val blackJackScoringStrategy: BlackJackScoringStrategy,
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun start() {
        val playerNames = inputView.getPlayers()
        val players = playerNames.map { Player(it) }
        dealer.provideCard(players, 2)
        outputView.printInitialCardCasting(players, 2)

        players.forEach { attemptCasting(it) }
        score(players)
    }

    private fun attemptCasting(player: Player) {
        while (ableToCastMore(player) && inputView.askCastCardToPlayer(player)) {
            dealer.provideCard(listOf(player), 1)
            outputView.printPlayerCards(player)
        }
    }

    private fun ableToCastMore(player: Player): Boolean {
        return blackJackScoringStrategy.score(player.cards) < BLACK_JACK_GOAL_NUMBER
    }

    private fun score(players: List<Player>) {
        players.forEach {
            outputView.printBlackJackResult(it, blackJackScoringStrategy.score(it.cards))
        }
    }

    companion object {
        private const val BLACK_JACK_GOAL_NUMBER = 21
    }
}
