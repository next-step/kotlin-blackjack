package blackjack

import blackjack.card.deck.PlayerCardDeck
import blackjack.card.score.BlackJackScoringStrategy
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val dealer: Dealer,
    private val blackJackScoringStrategy: BlackJackScoringStrategy,
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun start() {
        val playerNames = inputView.getPlayers()
        val players = playerNames.map { Player(it, PlayerCardDeck()) }

        players.forEach { player ->
            val cards = dealer.provideInitialCards()
            player.hit(cards)
        }

        outputView.printInitialCardCasting(players, INITIAL_CARD_NUM)

        players.forEach { attemptCasting(it) }
        score(players)
    }

    private fun attemptCasting(player: Player) {
        while (player.ableToCastCard(blackJackScoringStrategy) && inputView.askCastCardToPlayer(player)) {
            val card = dealer.provideCard()
            player.hit(card)
            outputView.printPlayerCards(player)
        }
    }

    private fun score(players: List<Player>) {
        players.forEach {
            outputView.printBlackJackResult(it, it.score(blackJackScoringStrategy))
        }
    }

    companion object {
        private const val INITIAL_CARD_NUM = 2
    }
}
