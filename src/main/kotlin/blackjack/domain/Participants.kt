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
        val dealers = participants.filterIsInstance<Dealer>()
        require(dealers.size == 1) { "게임 내 딜러는 한명만 참여 가능합니다." }
    }

    private fun requiresAtLeastOneGambler() {
        val gamblers = extractGamblers()
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

    fun extractNames(): List<String> {
        return participants.map { participant -> participant.name }
    }

    fun extractDealer(): Dealer {
        return participants.filterIsInstance<Dealer>().first()
    }

    companion object {
        fun of(
            dealer: Dealer,
            gamblers: List<Gambler>,
        ): Participants {
            val participants = listOf(dealer) + gamblers
            return Participants(participants)
        }
    }
}
