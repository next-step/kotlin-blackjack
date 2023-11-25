package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand
import blackjack.domain.User

class Hit(
    hand: Hand,
) : Started(hand) {

    override fun draw(card: Card): State2 {
        hand.receive(card)
        return if (hand.getSum() > User.BLACKJACK) {
            Bust(hand)
        } else {
            Hit(hand)
        }
    }

    override fun stay(): State2 {
        return Stay(hand)
    }

    override fun isFinished(): Boolean {
        return false
    }
}
