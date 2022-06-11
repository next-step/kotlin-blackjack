package camp.nextstep.blackjack.game

import camp.nextstep.blackjack.CardShuffler
import camp.nextstep.blackjack.Player
import camp.nextstep.blackjack.card.Card
import camp.nextstep.blackjack.card.CardDeck

class BlackJackGame private constructor(private var _cardDeck: CardDeck) {

    private val _participants = mutableListOf<Player>()

    val cardDeck get() = CardDeck.of(_cardDeck.cards)

    val participants get() = _participants.toList()

    fun participate(player: Player) {
        _participants.add(player)
    }

    fun start() {
        _cardDeck = CardShuffler.shuffle(_cardDeck)

        repeat(2) {
            for (player in _participants) {
                val drewCard = _cardDeck.draw()
                serve(player, drewCard)
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
