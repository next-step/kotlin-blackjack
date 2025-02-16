package blackjack.domain

class Participants(private val participants: List<Participant>) {
    val elements: List<Participant>
        get() = participants.toList()

    init {
        require(participants.isNotEmpty()) { "참여자는 비어있을 수 없습니다." }
        requiresOnlyOneDealer()
        requiresAtLeastOneGambler()
    }

    private fun requiresOnlyOneDealer() {
        val dealers = participants.filter { participant -> participant.isDealer() }
        require(dealers.size == MAXIMUM_NUMBER_OF_DEALERS) { "게임 내 딜러는 한명만 참여 가능합니다." }
    }

    private fun requiresAtLeastOneGambler() {
        val gamblers = participants.filter { participant -> !participant.isDealer() }
        require(gamblers.isNotEmpty()) { "게임 내 겜블러는 한명 이상 참여해야 합니다." }
    }

    fun receiveTwoCardsEach(deck: Deck) {
        participants.forEach { participant ->
            participant.receive(deck.draw(), deck.draw())
        }
    }

    fun extractGamblers(): List<Gambler> {
        return participants.filterIsInstance<Gambler>()
    }

    fun extractDealer(): Dealer {
        return participants.filterIsInstance<Dealer>().first()
    }

    companion object {
        const val MAXIMUM_NUMBER_OF_DEALERS = 1

        fun of(
            dealer: Dealer,
            gamblers: List<Gambler>,
        ): Participants {
            val participants = gamblers + dealer
            return Participants(participants)
        }
    }
}
