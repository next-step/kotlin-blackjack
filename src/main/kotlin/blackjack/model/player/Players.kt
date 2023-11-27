package blackjack.model.player

import blackjack.model.blackjack.BlackJackStatus
import blackjack.model.card.pack.Pack
import blackjack.model.player.playable.impl.Dealer
import blackjack.model.player.playable.impl.Player
import blackjack.model.result.PlayableResult

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
        val burstPlayers: List<Boolean> = values.map { it.status() == BlackJackStatus.ALIVE }.toList()
        return burstPlayers.contains(true)
    }

    fun count(): Int {
        return values.size
    }

    fun results(dealer: Dealer): List<Pair<Player, PlayableResult>> {
        return this.values.map { player -> player to player.result(dealer) }
    }
}
