package blackjack.domain.game

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import blackjack.view.ResultView

class BlackJackGame(
    cardDeck: CardDeck,
    players: List<Player>,
    private val takeMore: TakeMoreStrategy,
    private val resultView: ResultView
) {
    private var cardDeck: CardDeck
    private var _players: List<Player>

    val players: List<Player>
        get() = _players

    init {
        this.cardDeck = cardDeck

        players.map {
            it.receivedCards.add(cardDeck.pickCard())
            it.receivedCards.add(cardDeck.pickCard())
        }

        _players = players
        resultView.printInitDistributed(_players)
    }

    fun start() {
        calculateScore()
        moreGames()
    }

    private fun calculateScore() {
        _players.map { calculateScoreByPlayer(it) }
    }

    private fun calculateScoreByPlayer(player: Player) {
        player.calculateScore()
    }

    private fun moreGames() {
        _players.filter { it.canMoreGame() }
            .map { moreGamesByPlayer(it) }

        resultView.printCardsByPlayers(_players, true)
    }

    private fun moreGamesByPlayer(player: Player) {
        while (player.canMoreGame() && takeMore.wantToTake(player)) {
            pickCardByPlayer(player)
            calculateScoreByPlayer(player)
            resultView.printCardsByPlayer(player, false)
        }
    }

    private fun pickCardByPlayer(player: Player) {
        player.receivedCards.add(cardDeck.pickCard())
    }
}
