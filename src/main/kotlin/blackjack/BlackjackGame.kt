package blackjack

import blackjack.card.deck.PlayerCardDeck
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
        val players = playerNames.map { Player(it, PlayerCardDeck()) }
        dealer.provideCard(players, 2)
        outputView.printInitialCardCasting(players, 2)

        players.forEach { attemptCasting(it) }
        score(players)
    }

    private fun attemptCasting(player: Player) {
        while (player.ableToCastCard(blackJackScoringStrategy) && inputView.askCastCardToPlayer(player)) {
            dealer.provideCard(listOf(player), 1)
            outputView.printPlayerCards(player)
        }
    }

    private fun score(players: List<Player>) {
        players.forEach {
            outputView.printBlackJackResult(it, it.score(blackJackScoringStrategy))
        }
    }
}
