package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.PlayerCardDeckCapture
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerNames
import blackjack.domain.player.captureAllCardDecks
import blackjack.domain.shuffle.Shuffler
import java.lang.IllegalStateException
import java.util.LinkedList

class BlackJackGame(
    shuffler: Shuffler<Card>,
    playerNames: PlayerNames,
) {

    private val cardDeck = CardDeck(shuffler)
    private val waitPlayers = LinkedList(playerNames.map { Player(it) })
    private val finishedPlayers = LinkedList<Player>()

    fun distributeCardsToPlayers(): CardDistributionResult {
        require(needCardDistribution()) {
            "already card distribution completed"
        }
        waitPlayers.forEach { player ->
            val cards = cardDeck.pick(CARD_DISTRIBUTION_SIZE)
            player.pass(cards)
        }
        return CardDistributionResult(
            playerCardDeckCaptures = waitPlayers.captureAllCardDecks()
        )
    }

    fun nextTurn(): BlackJackGameTurn {
        require(isCardDistributionCompleted()) {
            "need card distribution. please call distributeCardsToPlayers()"
        }

        val waitPlayer = findWaitPlayerOrNull()
        if (waitPlayer != null) {
            return BlackJackGameTurn.HitAnswerWait(
                playerName = waitPlayer.name,
            )
        }

        return BlackJackGameTurn.Finish
    }

    fun hitFocusedPlayer(): PlayerCardDeckCapture {
        val player = findWaitPlayerOrNull() ?: throw IllegalStateException("focused player not existed")
        player.pass(cardDeck.pick())
        if (player.isBust()) {
            moveFocusedPlayerToFinished()
        }
        return player.captureCardDeck()
    }

    fun stayFocusedPlayer() {
        moveFocusedPlayerToFinished()
    }

    private fun moveFocusedPlayerToFinished() {
        val player = waitPlayers.removeFirstOrNull() ?: throw IllegalStateException("focused player not existed")
        finishedPlayers.add(player)
    }

    private fun needCardDistribution(): Boolean {
        return isCardDistributionCompleted().not()
    }

    private fun isCardDistributionCompleted(): Boolean {
        return waitPlayers.find { player -> player.notHasCard() } == null
    }

    private fun findWaitPlayerOrNull(): Player? {
        return waitPlayers.firstOrNull()
    }

    companion object {

        private const val CARD_DISTRIBUTION_SIZE = 2
    }
}
