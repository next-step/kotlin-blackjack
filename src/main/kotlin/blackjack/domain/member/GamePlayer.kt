package blackjack.domain.member

import blackjack.domain.Cards
import blackjack.domain.Deck
import blackjack.domain.member.Players.Companion.toPlayers

class GamePlayer(name: String, cards: Cards) : Player(name, cards) {

    companion object {
        fun init(usersNames: List<String>, deck: Deck): Players {
            return usersNames.map { name ->
                val cards = deck.drawInitAssignCards()
                GamePlayer(name, cards)
            }.toPlayers()
        }
    }
}
