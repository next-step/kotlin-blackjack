package blackjack

import blackjack.domain.Card
import blackjack.domain.Deck
import blackjack.domain.DeckBuilder
import blackjack.domain.Players
import blackjack.ui.RoundResult
import blackjack.ui.UserCards
import blackjack.ui.ViewResult

data class CardGame(private val deck: Deck, private val players: Players) {
    fun playerAllDeal() {
        players.forEach { player ->
            initGame(player.name)
        }
    }

    fun deal(name: String) {
        players.deal(players.find(name), deck.pop())
    }

    fun userCardOf(name: String): UserCards {
        return groupCardsByRank(players.findCardOf(name).values())
    }

    fun roundResult(): RoundResult {
        return players.associate { player ->
            player.name to groupCardsByRank(player.totalCards.values())
        }
    }

    fun result(): ViewResult {
        return players.associate { player ->
            player.name to mapOf(groupCardsByRank(player.totalCards.values()) to player.score())
        }
    }

    fun isBust(name: String): Boolean {
        return players.isBust(name)
    }

    private fun initGame(name: String) {
        val roundCards = deck.popCards(INITIAL_CARD_COUNT)
        players.deal(players.find(name), roundCards)
    }

    private fun groupCardsByRank(cards: List<Card>) =
        cards.groupBy { it.rank.symbol }
            .map { (rank, cards) ->
                rank to cards.map { it.suit.name }
            }.toMap()

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
