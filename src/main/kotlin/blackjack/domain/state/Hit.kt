package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

class Hit(
    hand: Hand,
) : Started(hand) {

    override fun draw(card: Card): State {
        hand.receive(card)
        return if (hand.getSum() > Blackjack.NUMBER) {
            Bust(hand)
        } else {
            Hit(hand)
        }
    }

    override fun stay(): State {
        return Stay(hand)
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun isBust(): Boolean {
        return false
    }

    override fun isBlackjack(): Boolean {
        return false
    }
}
