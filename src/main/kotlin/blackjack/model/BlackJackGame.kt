package blackjack.model

import blackjack.model.card.CardDeck

class BlackJackGame(
    private val _players: Players,
    private val cardDeck: CardDeck
) {
    fun gameBatting() {
        _players.gameBatting(cardDeck)
    }

    fun getPlayersStatus() = _players

    fun playsTurn() = _players.players

    fun playHit(player: Player) {
        player.hit(cardDeck.popCard())
    }
}
