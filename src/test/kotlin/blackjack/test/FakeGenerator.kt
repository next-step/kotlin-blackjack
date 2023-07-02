package blackjack.test

import blackjack.domain.Card
import blackjack.domain.Player

object FakeGenerator {
    fun player(name: String? = null, cards: List<Card>? = null): Player {
        val playerName = name ?: randomString(size = 3)
        val playerCards = cards ?: emptyList()
        return Player(playerName, playerCards)
    }

    fun randomString(size: Int): String {
        val allCharacters = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return allCharacters.shuffled().subList(0, size).joinToString("")
    }
}
