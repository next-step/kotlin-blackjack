package blackjack.domain.player

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
        players.forEach { it.drawCards(deck.drawFirstTurn()) }
    }

    fun isExistWaitingPlayer(): Boolean {
        return players.any { it.isAbleToDraw() }
    }
}
