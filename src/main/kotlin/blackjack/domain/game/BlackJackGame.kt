package blackjack.domain.game

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player

class BlackJackGame(
    private var cardDeck: CardDeck,
    players: List<Player>,
    private val takeMore: TakeMoreStrategy
) {
    private var _players: List<Player>

    val players: List<Player>
        get() = _players

    init {
        players.map {
            it.receivedCards.add(cardDeck.pickCard())
            it.receivedCards.add(cardDeck.pickCard())
        }

        _players = players
    }

    fun start() {
        calculateScore()
    }

    fun playersToPlay(): List<Player> {
        return _players.filter { it.canMoreGame() }
    }

    fun moreGamesByPlayer(player: Player) {
        pickCardByPlayer(player)
        calculateScoreByPlayer(player)
    }

    fun wantToTake(player: Player): Boolean {
        return this.takeMore.wantToTake(player)
    }

    private fun calculateScore() {
        _players.map { calculateScoreByPlayer(it) }
    }

    private fun calculateScoreByPlayer(player: Player) {
        player.calculateScore()
    }

    private fun pickCardByPlayer(player: Player) {
        player.receivedCards.add(cardDeck.pickCard())
    }
}
