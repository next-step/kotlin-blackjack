package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.player.GamePlayer
import blackjack.domain.player.GamePlayers

class BlackjackGame(val gamePlayers: GamePlayers) {
    val deck: CardDeck = CardDeck()

    fun initPlayers() {
        gamePlayers.receiveCard { deck.getRandomCards(INIT_CARD_COUNT) }
    }

    fun dealCard(gamePlayer: GamePlayer) {
        val card = deck.getRandomCard()
        gamePlayer.receiveCard(card)
    }

    fun finishPlayers(): BlackjackGameResult {
        return gamePlayers.finish()
    }

    companion object {
        const val DEAL_CARD_COUNT = 1
        const val INIT_CARD_COUNT = 2
    }
}
