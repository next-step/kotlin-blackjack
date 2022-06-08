package blackjack.domain.game

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players

class BlackJackGame(
    private var cardDeck: CardDeck,
    private var _playerList: Players
) {

    val players: List<Player>
        get() = _playerList.players

    init {
        _playerList.players
            .map {
                it.receivedCards.add(cardDeck.pickCard())
                it.receivedCards.add(cardDeck.pickCard())
            }
    }

    fun playDealer() {
        val dealer = _playerList.players.filterIsInstance<Dealer>().first()

        while (dealer.canBeTakeOneCard()) {
            dealer.addCard(cardDeck.pickCard())
        }
    }

    fun calculateWinner() {
        val dealer = _playerList.players.filterIsInstance<Dealer>().first()
        val players = _playerList.players.filter { it !is Dealer }

        if (dealer.score > BLACKJACK_SCORE) {
            players.map { it.isWinner = true }
            dealer.lose = players.size
            return
        }

        dealer.lose = players.filter { it.score > dealer.score }.map { it.isWinner = true }.count()
        dealer.win = players.filter { it.score < dealer.score }.map { it.isWinner = false }.count()
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
    }
}
