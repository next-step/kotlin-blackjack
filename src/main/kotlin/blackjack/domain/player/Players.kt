package blackjack.domain.player

import blackjack.domain.card.CardDeck

class Players(playerNames: List<String>, cardDeck: CardDeck) {
    val players: List<Player>

    init {
        players = playerNames
            .map { Player(it, cardDeck) }
            .toMutableList()
    }

    fun playersToPlay(): List<Player> {
        return players.filter { it.canMoreGame() }
    }
}
