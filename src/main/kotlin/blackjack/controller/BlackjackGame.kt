package blackjack.controller

import blackjack.model.BlackjackPlayer
import blackjack.model.BlackjackPlayerConsumer
import blackjack.model.BlackjackPlayers
import blackjack.model.BlackjackPlayersCardCountConsumer
import blackjack.model.BlackjackPlayersScoreConsumer
import blackjack.model.CardDeck
import blackjack.model.MoreWantedCardPredicate
import blackjack.model.PlayerName
import blackjack.model.PlayerNamesProvider

data class BlackjackGame(
    private val moreWantedCardPredicate: MoreWantedCardPredicate,
    private val playerNamesProvider: PlayerNamesProvider,
    private val blackjackPlayerConsumer: BlackjackPlayerConsumer,
    private val blackjackPlayersCardCountConsumer: BlackjackPlayersCardCountConsumer,
    private val blackjackPlayersScoreConsumer: BlackjackPlayersScoreConsumer,
) {
    fun start() {
        val deck = CardDeck()

        BlackjackPlayers(
            deck,
            blackjackPlayersCardCountConsumer,
            playerNamesProvider.names().map {
                BlackjackPlayer(PlayerName(it), blackjackPlayerConsumer, moreWantedCardPredicate)
            },
        ).apply {
            forEach { it.draw(deck) }
        }.also {
            blackjackPlayersScoreConsumer.consumePlayers(it)
        }
    }
}
