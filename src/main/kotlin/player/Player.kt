package player

import card.Card
import card.Hand
import card.TwoCards

sealed class Player private constructor(
    open val name: Name,
    open val hand: Hand,
) {
    class Start(
        override val name: Name,
        override val hand: Hand,
    ) : Player(name, hand) {
        fun addTwoCard(twoCards: TwoCards): OnGoing {
            return OnGoing(name, hand.addTwoCards(twoCards))
        }
    }

    class OnGoing(
        override val name: Name,
        override val hand: Hand,
    ) : Player(name, hand) {
        fun addCard(card: Card): Player {
            val newHand = hand.addCard(card)
            return if (newHand.isBust()) {
                Bust(name, newHand)
            } else {
                OnGoing(name, hand.addCard(card))
            }
        }

        fun toStay(): Stay = Stay(name, hand)
    }

    class Bust(
        override val name: Name,
        override val hand: Hand,
    ) : Player(name, hand)

    class Stay(
        override val name: Name,
        override val hand: Hand,
    ) : Player(name, hand)
}
