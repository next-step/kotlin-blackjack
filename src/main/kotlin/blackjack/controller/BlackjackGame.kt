package blackjack.controller

import blackjack.model.BlackjackDealer
import blackjack.model.BlackjackPlayer
import blackjack.model.BlackjackPlayers
import blackjack.model.BlackjackScoreJudge
import blackjack.model.CardDeck
import blackjack.model.PlayerName
import blackjack.model.TrumpCard

data class BlackjackGame(
    private val moreWantedCardPredicate: MoreWantedCardPredicate,
    private val playerNamesProvider: PlayerNamesProvider,
    private val blackjackPlayerConsumer: BlackjackPlayerConsumer,
    private val blackjackPlayersCardCountConsumer: BlackjackPlayersCardCountConsumer,
    private val blackjackPlayersScoreConsumer: BlackjackPlayersScoreConsumer,
) {
    fun start() {
        val players = BlackjackPlayers(
            playerNamesProvider.names()
                .map { BlackjackPlayer(PlayerName(it), BlackjackScoreJudge) }
        )
        val dealer = BlackjackDealer(CardDeck(TrumpCard.ALL), DEALER_CARD_SELECTOR)

        dealInitialCount(players, dealer)
            .map { dealMoreCard(it, dealer) }
            .let { BlackjackPlayers(it) }
            .also { blackjackPlayersScoreConsumer.consumePlayers(it) }
    }

    private fun dealInitialCount(
        players: BlackjackPlayers,
        dealer: BlackjackDealer,
    ): BlackjackPlayers {
        var dealtPlayers: BlackjackPlayers = players
        repeat(INITIAL_DEALING_COUNT) {
            dealtPlayers = BlackjackPlayers(dealtPlayers.map { it.addedCard(dealer.drawCard()) })
        }
        blackjackPlayersCardCountConsumer.consumePlayersCardCount(dealtPlayers, INITIAL_DEALING_COUNT)
        return dealtPlayers
    }

    private fun dealMoreCard(player: BlackjackPlayer, dealer: BlackjackDealer): BlackjackPlayer {
        var currentPlayer: BlackjackPlayer = player
        var isReceivedMoreCard = false
        while (isLessScoreThanLimit(currentPlayer) && moreWantedCardPredicate.isWantedMorePredicate(currentPlayer)) {
            currentPlayer = currentPlayer.addedCard(dealer.drawCard())
            blackjackPlayerConsumer.consumePlayer(currentPlayer)
            isReceivedMoreCard = true
        }
        if (!isReceivedMoreCard) {
            blackjackPlayerConsumer.consumePlayer(currentPlayer)
        }
        return currentPlayer
    }

    private fun isLessScoreThanLimit(player: BlackjackPlayer): Boolean =
        player.deckScore < BlackjackScoreJudge.LIMIT_SCORE

    companion object {
        private const val INITIAL_DEALING_COUNT: Int = 2

        private val DEALER_CARD_SELECTOR: (Collection<TrumpCard>) -> TrumpCard = { it.random() }
    }
}
