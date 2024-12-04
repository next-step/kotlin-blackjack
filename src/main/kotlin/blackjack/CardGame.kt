package blackjack

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.DeckBuilder
import blackjack.domain.MatchType
import blackjack.domain.Players
import blackjack.ui.DealerResult
import blackjack.ui.FinalWinnerResults
import blackjack.ui.Name
import blackjack.ui.RoundResult
import blackjack.ui.UIMatchType
import blackjack.ui.UserCards
import blackjack.ui.ViewResult

data class CardGame(private val deck: Deck, private val players: Players, val dealer: Dealer) {
    val dealerName: Name
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

    fun getPlayerCards(name: String): UserCards {
        return groupCardsByRank(players.findCardOf(name).values())
    }

    fun getRoundResults(): RoundResult {
        return players.associate { player ->
            player.name to groupCardsByRank(player.totalCards.values())
        }
    }

    fun getFinalRoundResults(): ViewResult {
        return players.associate { player ->
            player.name to mapOf(groupCardsByRank(player.totalCards.values()) to player.score())
        }
    }

    fun getDealerResults(): ViewResult {
        return mapOf(dealerName to mapOf(groupCardsByRank(dealer.totalCards.values()) to dealer.score()))
    }

    fun getFinalWinnerResults(): FinalWinnerResults {
        val playerResults = mutableMapOf<Name, UIMatchType>()
        var dealerWins = 0
        var dealerLosses = 0
        var dealerDraws = 0

        players.forEach { player ->
            val playerName = player.name

            when (player.isWin(dealer)) {
                MatchType.WIN -> {
                    playerResults[playerName] = UIMatchType.WIN
                    dealerLosses++
                }
                MatchType.LOSS -> {
                    playerResults[playerName] = UIMatchType.LOSS
                    dealerWins++
                }
                else -> {
                    playerResults[playerName] = UIMatchType.DRAW
                    dealerDraws++
                }
            }
        }

        val dealerResult = DealerResult(wins = dealerWins, losses = dealerLosses, draws = dealerDraws)
        return FinalWinnerResults(dealerResult = dealerResult, playerResults = playerResults)
    }

    fun isPlayerBust(name: String): Boolean {
        return players.isBust(name)
    }

    fun getAllPlayersNames(): List<Name> {
        return players.map { it.name }
    }

    private fun groupCardsByRank(cards: List<Card>) =
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
