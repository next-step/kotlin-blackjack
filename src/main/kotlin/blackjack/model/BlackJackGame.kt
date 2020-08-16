package blackjack.model

import blackjack.model.card.CardDeck
import blackjack.model.player.Dealer
import blackjack.model.player.Player

class BlackJackGame(
    private val players: Players,
    private val dealer: Dealer = Dealer(),
    private val cardDeck: CardDeck = CardDeck()
) {

    fun battingGame() {
        battingDealer()
        battingPlayers()
    }

    private fun battingDealer() {
        dealer.gameBatting(cardDeck.popDealerCardDummy())
    }

    private fun battingPlayers() =
        players.gameBatting {
            cardDeck.popPlayerCardDummy()
        }

    fun playsTurn(action: (Player) -> Unit) =
        players.runTurns(action)

    fun playHit(player: Player) {
        player.hit(cardDeck.popCard())
    }

    fun dealerTurn(showDealerTurn: () -> Unit) {
        while (dealer.canMoreCard()) {
            dealer.hit(cardDeck.popCard())
            showDealerTurn()
        }
        dealer.done()
    }

    fun getPlayerStatus() = mutableListOf<String>().apply {
        add(dealer.toString())
        addAll(players.toStringList())
    }
}
