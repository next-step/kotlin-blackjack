package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.player.vo.Name

class Participant(val name: Name) {
    val hand = Hand()

    infix fun receive(card: Card) {
        hand.add(card)
    }

    fun isBust(): Boolean {
        return hand.isBust()
    }

    companion object {
        fun from(name: String): Participant {
            return Participant(Name(name))
        }
    }
}
