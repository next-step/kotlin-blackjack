package blackjack.domain

sealed class Participant {
    open class Player(
        val name: String,
        val ownedCards: MutableList<Card> = mutableListOf(),
        private val actions: MutableList<HitCommand> = mutableListOf(),
    ) {
        fun receiveCard(card: Card) {
            ownedCards.add(card)
            actions.add(HitCommand.HIT)
        }

        fun stay() {
            actions.add(HitCommand.STAY)
        }

        fun hasBusted(): Boolean {
            return sumOfCard() <= 21
        }

        fun hasStayed(): Boolean {
            return actions.lastOrNull() == HitCommand.STAY
        }

        fun sumOfCard(): Int {
            var totalSum = 0
            ownedCards.forEach {
                totalSum += it.number.appendScore(totalSum)
            }
            return totalSum
        }
    }
}
