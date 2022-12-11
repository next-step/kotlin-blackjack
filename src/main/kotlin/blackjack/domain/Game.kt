package blackjack.domain

class Game(val players: Players, val deck: Deck = Deck()) {

    fun initialCard(): Players {
        return players.initialCard(deck)
    }
}
