package blackjack.model

import blackjack.model.card.CardDeck

class BlackJackGame(
    private val players: Players,
    private val cardDeck: CardDeck
) {
    fun gameRun(
        isHit: (Player) -> Boolean,
        showPlayer: (Player) -> Unit
    ): Players {
        players.gameBatting(
            cardDeck,
            showPlayer
        )

        players.processTurns(
            isHit,
            showPlayer,
            cardDeck
        )

        return players
    }
}
