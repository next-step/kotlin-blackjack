package blackjack

import blackjack.domain.Deck
import blackjack.domain.DeckBuilder
import blackjack.domain.Players

data class CardGame(private val deck: Deck, private val players: Players) {
    val playersSize: Int = players.size

    fun initGame(name: String) {
        val roundCards = deck.popCards(INITIAL_CARD_COUNT)
        players.deal(players.find(name), roundCards)
    }

    fun playerAllDeal() {
        players.forEach { player ->
            initGame(player.name)
        }
    }

    fun deal(name: String) {
        players.deal(players.find(name), deck.pop())
    }

    fun cardsOf(name: String): List<String> {
        return players.findCardOf(name).values().map { it.name }
    }

    fun scoreOf(name: String): Int {
        return players.scoreOf(name)
    }

    fun isBust(name: String): Boolean {
        return players.isBust(name)
    }

    fun result(): Map<String, Map<List<String>, Int>> {
        return players.associate { player ->
            player.name to
                    mapOf(
                        player.totalCards.values().map { it.name } to player.score(),
                    )
        }
    }

    companion object {
        const val INITIAL_CARD_COUNT = 2

        fun from(
            deck: Deck,
            users: List<String>,
        ): CardGame {
            return CardGame(deck, Players.from(users))
        }

        fun fromNames(names: List<String>): CardGame {
            return from(DeckBuilder.cachedDeck, names)
        }
    }
}
