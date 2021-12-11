package blackjack.setup

import blackjack.deck.CardDeck
import blackjack.entity.Player

object GameStartSetting {

    fun setGame(playerNames: List<String>): List<Player> {

        val players = mutableListOf<Player>()

        for (name in playerNames) {

            val cards = mutableListOf(CardDeck.draw(), CardDeck.draw())

            players.add(Player(name, cards))
        }
        return players
    }
}
