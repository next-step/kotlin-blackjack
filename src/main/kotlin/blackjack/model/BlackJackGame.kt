package blackjack.model

import blackjack.model.card.CardDeck
import blackjack.model.player.Player

class BlackJackGame(
    private val players: Players,
    private val cardDeck: CardDeck
) {
    fun gameBatting() =
        players.gameBatting {
            cardDeck.popTwoCard()
        }

    fun getPlayerStatus() = players.toString()

    fun playsTurn(action: (Player) -> Unit) =
        players.runTurns(action)

    fun playHit(player: Player) {
        player.hit(cardDeck.popCard())
    }
}
