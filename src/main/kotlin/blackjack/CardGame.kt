package blackjack

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.DeckBuilder
import blackjack.domain.Players
import blackjack.ui.UserCards
import blackjack.ui.UserName

data class CardGame(private val deck: Deck, private val players: Players, val dealer: Dealer) {
    val dealerName: UserName
        get() = dealer.name
    val dealerShouldAddCard: Boolean
        get() = dealer.shouldAddCard

    fun startGame() {
        dealer.receive(deck.popCards(INITIAL_CARD_COUNT))
        players.dealInitialCards(deck, INITIAL_CARD_COUNT)
    }

    fun dealCardToPlayer(name: String) {
        players.deal(players.find(name), deck.pop())
    }

    fun dealCardToDealer() {
        dealer.receive(Deck(listOf(deck.pop())))
    }

    fun playerCards(name: String): UserCards {
        return groupCardsByRank(players.findCardOf(name).values())
    }

    fun isPlayerBust(name: String): Boolean {
        return players.isBust(name)
    }

    fun allPlayersNames(): List<UserName> {
        return players.map { it.name }
    }

    private fun groupCardsByRank(cards: List<Card>): Map<String, List<String>> =
        cards.groupBy { it.rank.name }
            .map { (rank, cards) ->
                rank to cards.map { it.suit.name }
            }.toMap()

    fun resultEvaluator(): GameResultEvaluator {
        return GameResultEvaluator(players, dealer)
    }

    companion object {
        const val INITIAL_CARD_COUNT = 2

        fun create(users: List<String>): CardGame {
            return CardGame(DeckBuilder.cachedDeck, Players.from(users), Dealer())
        }

        fun from(
            deck: Deck,
            users: List<String>,
        ): CardGame {
            return CardGame(deck, Players.from(users), Dealer())
        }
    }
}
