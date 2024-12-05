package blackjack

import blackjack.domain.Deck
import blackjack.domain.Player

class BlackJackGame private constructor(
    val players: List<Player>,
    private val deck: Deck,
) {
    fun drawSingleCardToPlayer(player: Player): Boolean {
        return player.addCard(deck.draw())
    }

    fun hasBlackJackPlayer(): Boolean {
        return players.any { it.isBlackJack() }
    }

    companion object {
        private const val DEFAULT_CARD_COUNT = 2

        fun createGame(
            players: List<Player>,
            deck: Deck,
        ): BlackJackGame {
            return BlackJackGame(setPlayersDefaultCards(players, deck), deck)
        }

        private fun setPlayersDefaultCards(
            players: List<Player>,
            deck: Deck,
        ): List<Player> {
            players.forEach { player -> repeat(DEFAULT_CARD_COUNT) { player.addCard(deck.draw()) } }
            return players
        }
    }
}
