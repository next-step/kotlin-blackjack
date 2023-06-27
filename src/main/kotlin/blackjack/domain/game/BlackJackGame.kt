package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.gamer.CardHolder
import blackjack.domain.gamer.PlayerCards
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Player
import blackjack.domain.gamer.PlayerNames
import blackjack.domain.gamer.captureAllCards
import blackjack.domain.shuffle.Shuffler
import java.util.LinkedList
import kotlin.IllegalStateException

class BlackJackGame(
    shuffler: Shuffler<Card>,
    playerNames: PlayerNames,
) {

    private val cardDeck = CardDeck.create(shuffler)
    private val dealer = Dealer()
    private val players = LinkedList(playerNames.map { Player(it) })

    fun currentTurn(): BlackJackGameTurn {
        return when {
            needCardDistribution() -> {
                BlackJackGameTurn.CardDistribution
            }
            hasWaitPlayer() -> {
                BlackJackGameTurn.PlayerAnswer(requireWaitPlayer().name)
            }
            isDealerWait() -> {
                BlackJackGameTurn.Dealer
            }
            else -> {
                BlackJackGameTurn.Finished
            }
        }
    }

    fun distributeCardsToPlayers(): CardDistributionResult {
        requireTurn<BlackJackGameTurn.CardDistribution>()

        dealer.pass(cardDeck.pick(CARD_DISTRIBUTION_SIZE))
        players.forEach { player ->
            player.pass(cardDeck.pick(CARD_DISTRIBUTION_SIZE))
        }

        return CardDistributionResult(
            distributionCardSize = CARD_DISTRIBUTION_SIZE,
            dealerCards = listOf(
                CardHolder.Open(dealer.cards.first()),
                CardHolder.Hide,
            ),
            playerCards = players.captureAllCards(),
        )
    }

    fun hitFocusedPlayer(): PlayerCards {
        requireTurn<BlackJackGameTurn.PlayerAnswer>()

        val player = requireWaitPlayer()
        player.pass(cardDeck.pick())
        return player.captureCards()
    }

    fun stayFocusedPlayer() {
        requireTurn<BlackJackGameTurn.PlayerAnswer>()

        requireWaitPlayer().stay()
    }

    fun executeDealerTurn(): DealerTurnExecuteResult {
        requireTurn<BlackJackGameTurn.Dealer>()

        val isDistributedOneMoreCard = if (dealer.canHit()) {
            dealer.pass(cardDeck.pick())
            true
        } else {
            dealer.stay()
            false
        }

        return DealerTurnExecuteResult(
            isDistributedOneMoreCard = isDistributedOneMoreCard,
        )
    }

    fun makeGameResult(): BlackJackGameResult {
        requireTurn<BlackJackGameTurn.Finished>()

        return BlackJackGameResult(
            dealerGameResult = DelayerGameResult(dealer.cards),
            playerGameResults = players
                .captureAllCards()
                .map { playerCards -> PlayerGameResult(playerCards) },
            matchResult = createMatchResult()
        )
    }

    private inline fun <reified T> requireTurn() {
        val turn = currentTurn()
        require(turn is T) {
            "you want turn is '${turn::class.java.simpleName}'. but current turn is '${T::class.java.simpleName}'"
        }
    }

    private fun needCardDistribution(): Boolean {
        return players.all { player -> player.notHasCard() } && dealer.notHasCard()
    }

    private fun hasWaitPlayer(): Boolean {
        return findWaitPlayerOrNull() != null
    }

    private fun requireWaitPlayer(): Player {
        return findWaitPlayerOrNull() ?: throw IllegalStateException("wait player not existed")
    }

    private fun findWaitPlayerOrNull(): Player? {
        return players.firstOrNull { it.state.canHit() }
    }

    private fun isDealerWait(): Boolean {
        return dealer.state.canHit()
    }

    private fun createMatchResult(): MatchResult {
        val playerMatchResults = players.map { player -> player.match(dealer) }
        val dealerMatchResult = DealerMatchResult(
            winCount = playerMatchResults.count { it.matchResultType == MatchResultType.LOSE },
            tieCount = playerMatchResults.count { it.matchResultType == MatchResultType.TIE },
            loseCount = playerMatchResults.count { it.matchResultType == MatchResultType.WIN }
        )
        return MatchResult(
            dealerMatchResult = dealerMatchResult,
            playerMatchResults = playerMatchResults,
        )
    }

    companion object {

        private const val CARD_DISTRIBUTION_SIZE = 2
    }
}
