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

    class Dealer(
        private val deck: Deck,
        private val displayName: String = "Dealer",
    ) : Player(displayName) {
        fun deal(participants: Participants) {
            repeat(NUMBER_OF_DEAL_CARD) {
                dealOneCardToEachPlayer(participants)
            }
        }

        private fun dealOneCardToEachPlayer(participants: Participants) {
            participants.allPlayers().forEach {
                it.receiveCard(deck.draw())
            }
        }

        fun hit(player: Player) {
            player.receiveCard(deck.draw())
        }

        companion object {
            private const val NUMBER_OF_DEAL_CARD = 2
        }
    }
}
