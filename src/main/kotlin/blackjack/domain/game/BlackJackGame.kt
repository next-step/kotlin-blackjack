package blackjack.domain.game

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players

class BlackJackGame(
    cardDeck: CardDeck,
    private val _players: Players,
    private val _dealer: Dealer
) {

    val players: List<Player>
        get() = _players.players

    val dealer: Dealer
        get() = _dealer

    init {
        firstDeal(cardDeck)
    }

    private fun firstDeal(cardDeck: CardDeck) {
        (players + dealer).map {
            it.addCard(cardDeck.pickCard())
            it.addCard(cardDeck.pickCard())
        }
    }
}
