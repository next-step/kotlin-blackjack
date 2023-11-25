package blackjack.model

import blackjack.model.pack.Pack
import blackjack.model.playable.PlayableResult
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

    fun isGameOver(): Boolean {
        return values.any { Referee.isBurst(it) }
    }

    fun count(): Int {
        return values.size
    }

    fun scoreBattle(dealerScore: Int): Map<Player, PlayableResult> {
        return values.associateWith { Referee.playerResult(it, dealerScore) }
    }

    fun walkover(): Map<Player, PlayableResult> {
        return values.associateWith { PlayableResult.WIN }
    }
}
