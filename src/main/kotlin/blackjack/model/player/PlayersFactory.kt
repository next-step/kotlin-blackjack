package blackjack.model.player

import blackjack.model.trump.Cards
import blackjack.model.trump.Deck

object PlayersFactory {
    fun create(playerNames: List<String>, deck: Deck): Players {
        return Players.Builder().players(playerNames.map { Player(Cards.firstDraw(deck), it) }).build()
    }
}
