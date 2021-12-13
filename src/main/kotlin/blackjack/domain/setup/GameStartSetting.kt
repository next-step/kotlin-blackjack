package blackjack.domain.setup

import blackjack.domain.deck.CardDeck
import blackjack.domain.entity.Player

object GameStartSetting {

    fun setGame(playerNames: List<String>, cardDeck: CardDeck = CardDeck()): List<Player> {

        val players = mutableListOf<Player>()

        for (name in playerNames) {

            val cards = mutableListOf(cardDeck.draw(), cardDeck.draw())

            players.add(Player(name, cards))
        }
        return players
    }
}
