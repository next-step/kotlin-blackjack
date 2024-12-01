package blackjack.service

import blackjack.domain.Card
import blackjack.domain.Player

class BlackJackService {
    private val card = Card()

    fun createPlayers(names: List<String>): List<Player> {
        return names.map { Player(it) }
    }

    fun distributeInitialCards(players: List<Player>) {
        repeat(2) {
            players.forEach { player ->
                val drawnCards = card.drawCards(1)
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
}
