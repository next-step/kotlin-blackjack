package blackjack.service

import blackjack.domain.Card
import blackjack.domain.Deck
import blackjack.domain.Player

class BlackJackService {
    val card = Card()
    val deck = Deck()

    fun createPlayers(names: List<String>): List<Player> {
        return names.map { Player(it) }
    }

    fun distributeInitialCards(players: List<Player>) {
        repeat(2) {
            players.forEach { player ->
                val drawnCards = deck.drawCards(1)
                player.addCards(drawnCards)
            }
        }
    }

    fun splitPlayerNames(input: String): List<String> {
        if (input.isBlank()) {
            return emptyList()
        }
        return input.split(",").map { it.trim() }
    }

    fun calculateScores(players: List<Player>): List<Pair<String, Int>> {
        return players.map { player ->
            val score = Card.calculateCardValue(player.cards)
            player.name to score
        }
    }
}
