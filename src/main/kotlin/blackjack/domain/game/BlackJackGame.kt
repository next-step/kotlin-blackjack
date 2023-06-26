package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardHolder
import blackjack.domain.card.PlayerCards
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerNames
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
        require(needCardDistribution()) {
            "already card distributed"
        }
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
        checkPlayerTurn()
        val player = requireWaitPlayer()
        player.pass(cardDeck.pick())
        return player.captureCards()
    }

    fun stayFocusedPlayer() {
        checkPlayerTurn()
        requireWaitPlayer().stay()
    }

    fun executeDealerTurn(): DealerTurnExecuteResult {
        checkDelayerTurn()

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
        checkFinishTurn()

        return BlackJackGameResult(
            playerGameResults = players
                .captureAllCards()
                .map { playerCards -> PlayerGameResult(playerCards) },
        )
    }

    private fun checkPlayerTurn() {
        require(isCardDistributionCompleted()) {
            "need card distribute"
        }
    }

    private fun checkDelayerTurn() {
        require(isCardDistributionCompleted()) {
            "need card distribute"
        }
        require(players.all { player -> player.state.canHit().not() }) {
            "can hit player is remaining"
        }
    }

    private fun checkFinishTurn() {
        require(isCardDistributionCompleted()) {
            "need card distribute"
        }
        require(players.all { player -> player.state.canHit().not() }) {
            "can hit player is remaining"
        }
        require(dealer.state.canHit().not()) {
            "should be start dealer turn"
        }
    }

    private fun isCardDistributionCompleted(): Boolean {
        return needCardDistribution().not()
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

    private fun List<Player>.captureAllCards(): List<PlayerCards> {
        return map { it.captureCards() }
    }

    private fun Player.captureCards(): PlayerCards {
        return PlayerCards(
            playerName = name,
            cards = cards,
        )
    }

    private fun isDealerWait(): Boolean {
        return dealer.state.canHit()
    }

    companion object {

        private const val CARD_DISTRIBUTION_SIZE = 2
    }
}
