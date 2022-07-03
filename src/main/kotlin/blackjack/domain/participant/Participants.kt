package blackjack.domain.participant

import blackjack.domain.Deck

@JvmInline
value class Participants(
    val participants: List<Participant>,
) {
    init {
        val distinctPlayerNames = participants.map { it.name }.toSet()
        require(participants.size == distinctPlayerNames.size) { "플레이어의 이름이 중복되면 안됩니다." }
    }

    constructor(vararg names: String) : this(names.map { Player(it) })

    fun drawInitCards(deck: Deck) {
        participants.forEach { it.drawCards(deck.pullOutFirstTurn()) }
    }

    fun isExistWaitingPlayer(): Boolean {
        return participants.any { isWaitingPlayer(it) }
    }

    fun findCurrentTurnPlayer(): Participant =
        participants.firstOrNull { isWaitingPlayer(it) }
            ?: throw IllegalStateException("대기중인 플레이어가 존재하지 않습니다.")

    private fun isWaitingPlayer(it: Participant) = it is Player && it.isAbleToDraw()

    fun findDealer(): Participant =
        participants.firstOrNull { it is Dealer } ?: throw IllegalStateException("딜러가 존재하지 않습니다.")
}
