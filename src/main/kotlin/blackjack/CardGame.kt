package blackjack

import blackjack.domain.Deck
import blackjack.domain.Players

data class CardGame(private val deck: Deck, private val players: Players) {
    val playersSize: Int = players.size

    fun startGame(name: String) {
        val startCardCount = 2
        val roundCards = deck.popCards(startCardCount)
        players.deal(players.find(name),  roundCards)
    }

    fun userHand(name: String): Deck {
        return players.findCardOf(players.find(name))
    }

    fun pick(name: String) {
        val roundCards = deck.pop()
        players.deal(players.find(name), roundCards)
    }

    fun cardsOf(name: String): List<String> {
        return players.findCardOf(players.find(name)).values().map { it.name }
    }

    fun scoreOf(name: String): Int {
        return players.scoreOf(name)
    }

    companion object {
        fun from(deck: Deck, users: List<String>): CardGame {
            return CardGame(deck, Players.from(users))
        }
    }
}