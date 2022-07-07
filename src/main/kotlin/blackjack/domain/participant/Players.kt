package blackjack.domain.participant

import blackjack.domain.Deck

@JvmInline
value class Players(
    val players: List<Player>,
) {
    init {
        val distinctPlayerNames = players.map { it.name }.toSet()
        require(players.size == distinctPlayerNames.size) { "플레이어의 이름이 중복되면 안됩니다." }
    }

    constructor(vararg names: String) : this(names.map { Player(it) })

    fun drawInitCards(deck: Deck) {
        players.forEach { it.drawCards(deck.pullOutFirstTurn()) }
    }

    fun isExistWaitingPlayer(): Boolean {
        return players.any { it.isAbleToDraw() }
    }

    fun findCurrentTurnPlayer(): Participant =
        players.firstOrNull { it.isAbleToDraw() }
            ?: throw IllegalStateException("대기중인 플레이어가 존재하지 않습니다.")
}
