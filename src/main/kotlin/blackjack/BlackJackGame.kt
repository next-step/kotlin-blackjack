package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.PlayerStatus
import blackjack.domain.Players

class BlackJackGame private constructor(
    val dealer: Dealer,
    val players: Players,
    private val deck: Deck,
) {
    val isPlaying: Boolean
        get() = players.hasHitPlayer()
    var currentPlayer: Player
        private set

    init {
        currentPlayer = players.getNextPlayer()
    }

    fun hit(player: Player) {
        player.addCard(deck.draw())
    }

    fun stay(player: Player) {
        player.stay()
    }

    fun checkBlackJack(): Boolean {
        return players.hasBlackJackPlayer()
    }

    fun checkCurrentPlayerStatusAndChange() {
        while (players.playerIterator.hasNext() && currentPlayer.status != PlayerStatus.HIT) {
            changeToNextPlayer()
        }
    }

    private fun changeToNextPlayer() {
        currentPlayer = players.getNextPlayer()
    }

    fun handleDealer() {
        if (dealer.shouldHit()) {
            dealer.addCard(deck.draw())
        }
    }

    companion object {
        private const val DEFAULT_CARD_COUNT = 2

        fun createGame(
            playerNames: List<String>,
            deck: Deck,
        ): BlackJackGame {
            val dealer = createDealer(deck)
            val players = createPlayers(playerNames, deck)
            return BlackJackGame(dealer, players, deck)
        }

        private fun createPlayers(
            playerNames: List<String>,
            deck: Deck,
        ): Players {
            val players = Players.from(playerNames)
            players.drawDefaultCards(deck, DEFAULT_CARD_COUNT)
            return players
        }

        private fun createDealer(deck: Deck): Dealer {
            val dealer = Dealer()
            dealer.drawDefaultCards(deck, DEFAULT_CARD_COUNT)
            return dealer
        }
    }
}
