package blackjack.model

import blackjack.model.pack.Pack
import blackjack.model.playable.impl.Player
import blackjack.model.playblestrategy.PlayingStrategy

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
        val burstPlayers: List<Boolean> = values.map { it.isAlive() == BlackJackStatus.ALIVE }.toList()
        return burstPlayers.contains(true)
    }

    fun count(): Int {
        return values.size
    }

    fun playingTurn(playingStrategy: PlayingStrategy, pack: Pack) {
        values.forEach {
            if (this.isAlive(it)) {
                it.playing(playingStrategy, pack)
            }
        }
    }

    private fun isAlive(player: Player): Boolean {
        return player.isAlive() == BlackJackStatus.ALIVE
    }
}
