package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.PlayerCardDeckCapture
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerNames
import blackjack.domain.player.captureAllCardDecks
import blackjack.domain.shuffle.Shuffler
import java.util.LinkedList
import kotlin.IllegalStateException

class BlackJackGame(
    shuffler: Shuffler<Card>,
    playerNames: PlayerNames,
) {

    private val cardDeck = CardDeck.create(shuffler)
    private val waitPlayers = LinkedList(playerNames.map { Player(it) })
    private val finishedPlayers = LinkedList<Player>()

    fun currentTurn(): BlackJackGameTurn {
        return when {
            needCardDistribution() -> {
                BlackJackGameTurn.CardDistributionWait
            }
            hasWaitPlayer() -> {
                BlackJackGameTurn.HitAnswerWait(requireWaitPlayer().name)
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
        waitPlayers.forEach { player ->
            val cards = cardDeck.pick(CARD_DISTRIBUTION_SIZE)
            player.pass(cards)
        }
        return CardDistributionResult(
            playerCardDeckCaptures = waitPlayers.captureAllCardDecks(),
        )
    }

    fun hitFocusedPlayer(): PlayerCardDeckCapture {
        checkCardDistributionCompleted()

        val player = requireWaitPlayer()
        player.pass(cardDeck.pick())
        if (player.isBust()) {
            moveFocusedPlayerToFinished()
        }
        return player.captureCardDeck()
    }

    fun stayFocusedPlayer() {
        checkCardDistributionCompleted()
        moveFocusedPlayerToFinished()
    }

    fun makeGameResult(): BlackJackGameResult {
        require(waitPlayers.isEmpty()) {
            "game is not end"
        }

        val playerGameResults = finishedPlayers.map { player ->
            PlayerGameResult(player.captureCardDeck())
        }
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

    private fun needCardDistribution(): Boolean {
        return isCardDistributionCompleted().not()
    }

    private fun isCardDistributionCompleted(): Boolean {
        return waitPlayers.all { player -> player.hasCard() }
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

    companion object {

        private const val CARD_DISTRIBUTION_SIZE = 2
    }
}
