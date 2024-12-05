package blackjack

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.DeckBuilder
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.ui.UserCards
import blackjack.ui.UserName

data class CardGame(private val deck: Deck, private val players: Players, val dealer: Dealer) {
    val dealerName: UserName
        get() = dealer.name
    private val dealerShouldAddCard: Boolean
        get() = dealer.shouldAddCard

    fun startGame() {
        dealer.receive(deck.popCards(INITIAL_CARD_COUNT))
        players.dealInitialCards(deck, INITIAL_CARD_COUNT)
    }

    fun handleUserTurn(
        action: (
            Player,
            UserCards,
        ) -> Boolean,
    ) {
        players.forEach { currentUser ->
            while (action(currentUser, playerCards(currentUser.name))) {
                dealCardToPlayer(currentUser.name)
                if (isPlayerBust(currentUser.name)) break
            }
        }
    }

    fun handleDealerTurn(action: () -> Unit) {
        if (dealerShouldAddCard) {
            action()
            dealCardToDealer()
        }
    }

    fun dealCardToPlayer(name: String) {
        players.deal(players.find(name), deck.pop())
    }

    fun playerCards(name: String): UserCards {
        return groupCardsByRank(players.findCardOf(name).values())
    }

    fun resultEvaluator(): GameResultEvaluator {
        return GameResultEvaluator(players, dealer)
    }

    fun allPlayersNames(): List<UserName> {
        return players.map { it.name }
    }

    private fun dealCardToDealer() {
        dealer.receive(Deck(listOf(deck.pop())))
    }

    private fun isPlayerBust(name: String): Boolean {
        return players.isBust(name)
    }

    private fun groupCardsByRank(cards: List<Card>): Map<String, List<String>> =
        cards.groupBy { it.rank.name }
            .map { (rank, cards) ->
                rank to cards.map { it.suit.name }
            }.toMap()

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
