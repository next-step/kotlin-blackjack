package blackjack.domain

sealed class Participant(
    val ownedCards: MutableList<Card> = mutableListOf(),
    private var _status: ParticipantStatus = ParticipantStatus.PLAYING,
) {
    val status: ParticipantStatus
        get() =
            when {
                sumOfCard() > 21 -> ParticipantStatus.BUSTED
                else -> _status
            }

    fun receiveCard(card: Card) {
        ownedCards.add(card)
    }

    fun stay() {
        _status = ParticipantStatus.STAYED
    }

    fun sumOfCard(): Int {
        var totalSum = 0
        ownedCards.forEach {
            totalSum += it.number.appendScore(totalSum)
        }
        return totalSum
    }

    fun isBlackjack(): Boolean {
        return ownedCards.size == 2 && sumOfCard() == 21
    }

    class Player(val name: String, val bettingAmount: Int, ownedCards: MutableList<Card> = mutableListOf()) :
        Participant(
            ownedCards = ownedCards,
        )

    class Dealer(
        private val deck: Deck,
        ownedCards: MutableList<Card> = mutableListOf(),
    ) : Participant(ownedCards = ownedCards) {
        fun deal(participants: Participants) {
            repeat(NUMBER_OF_DEAL_CARD) {
                dealOneCardToEachPlayer(participants)
            }
        }

        private fun dealOneCardToEachPlayer(participants: Participants) {
            participants.members.forEach {
                it.receiveCard(deck.draw())
            }
        }

        fun giveCardTo(participant: Participant) {
            participant.receiveCard(deck.draw())
        }

        fun shouldDrawCard(): Boolean = sumOfCard() <= DRAW_LIMIT

        companion object {
            private const val NUMBER_OF_DEAL_CARD = 2
            private const val DRAW_LIMIT: Int = 16
        }
    }
}
