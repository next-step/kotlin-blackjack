package blackjack.model

import blackjack.model.pack.Pack
import blackjack.model.playable.impl.Player

class Players(
    val values: Set<Player>,
) {
    init {
        require(values.map { it.name }.distinct().size == values.size) { "Player 들의 이름은 중복이 허용되지 않습니다" }
    }

    constructor(vararg players: Player) : this(players.toSet())

    fun dealing(pack: Pack) {
        values.forEach { it.dealing(pack) }
    }

    fun hasAnyAlivePlayer(): Boolean {
        val burstPlayers: List<Boolean> = values.map { it.isBurst() }.toList()
        return !burstPlayers.contains(false)
    }

    fun count(): Int {
        return values.size
    }
}
