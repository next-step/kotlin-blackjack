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
    private val waitPlayers = LinkedList(playerNames.map { Player(it) })
    private val finishedPlayers = LinkedList<Player>()

    fun currentTurn(): BlackJackGameTurn {
        return when {
            needCardDistribution() -> {
                BlackJackGameTurn.CardDistribution
            }
            hasWaitPlayer() -> {
                BlackJackGameTurn.PlayerAnswer(requireWaitPlayer().name)
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
        waitPlayers.forEach { player ->
            player.pass(cardDeck.pick(CARD_DISTRIBUTION_SIZE))
        }

        return CardDistributionResult(
            distributionCardSize = CARD_DISTRIBUTION_SIZE,
            dealerCards = listOf(
                CardHolder.Open(dealer.cards.first()),
                CardHolder.Hide,
            ),
            playerCards = waitPlayers.captureAllCards(),
        )
    }

    fun hitFocusedPlayer(): PlayerCards {
        checkCardDistributionCompleted()

        val player = requireWaitPlayer()
        player.pass(cardDeck.pick())
        if (player.isBust()) {
            moveFocusedPlayerToFinished()
        }
        return player.captureCards()
    }

    fun stayFocusedPlayer() {
        checkCardDistributionCompleted()
        moveFocusedPlayerToFinished()
    }

    fun makeGameResult(): BlackJackGameResult {
        require(waitPlayers.isEmpty()) {
            "game is not end"
        }

        val playerGameResults = finishedPlayers
            .map { player -> player.captureCards() }
            .map { playerCards -> PlayerGameResult(playerCards) }

        return BlackJackGameResult(
            playerGameResults = playerGameResults,
        )
    }

    private fun checkCardDistributionCompleted() {
        require(isCardDistributionCompleted()) {
            "need card distribute"
        }
    }

    private fun moveFocusedPlayerToFinished() {
        val player = waitPlayers.removeFirstOrNull() ?: throw IllegalStateException("wait player not existed")
        finishedPlayers.add(player)
    }

    private fun isCardDistributionCompleted(): Boolean {
        return needCardDistribution().not()
    }

    private fun needCardDistribution(): Boolean {
        return waitPlayers.all { player -> player.notHasCard() } && dealer.notHasCard()
    }

    private fun hasWaitPlayer(): Boolean {
        return findWaitPlayerOrNull() != null
    }

    private fun requireWaitPlayer(): Player {
        return findWaitPlayerOrNull() ?: throw IllegalStateException("wait player not existed")
    }

    private fun findWaitPlayerOrNull(): Player? {
        return waitPlayers.firstOrNull()
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

    companion object {

        private const val CARD_DISTRIBUTION_SIZE = 2
    }
}
