package camp.nextstep.blackjack.game

import camp.nextstep.blackjack.card.Card
import camp.nextstep.blackjack.card.CardDeck
import camp.nextstep.blackjack.card.CardShuffler
import camp.nextstep.blackjack.player.Player

class BlackJackGame private constructor(private var _cardDeck: CardDeck) {

    private val _participants = mutableListOf<Player>()

    val cardDeck get() = CardDeck.of(_cardDeck.cards)

    val participants get() = _participants.toList()

    fun participate(player: Player) {
        _participants.add(player)
    }

    fun initialize() {
        _cardDeck = CardShuffler.shuffle(_cardDeck)

        repeat(2) {
            for (player in _participants) {
                serve(player, _cardDeck.draw())
            }
        }
    }

    private fun serve(to: Player, card: Card) {
        to.receive(card)
    }

    companion object {
        fun new(): BlackJackGame {
            return BlackJackGame(CardDeck.new())
        }
    }
}
