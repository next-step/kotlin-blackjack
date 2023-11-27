package blackjack.model.player

import blackjack.model.blackjack.BlackJackStatus
import blackjack.model.card.pack.Pack
import blackjack.model.player.playable.impl.Dealer
import blackjack.model.player.playable.impl.Player
import blackjack.model.player.playblestrategy.impl.ConsoleInputStrategy
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

    fun playingTurn(pack: Pack) {
        values.forEach { player ->
            if (player.status() == BlackJackStatus.ALIVE) {
                player.playing(ConsoleInputStrategy(player), pack)
            }
//            else {
//                println("   ### [검증용/삭제예정] 플레이어 ${player.name} 은 DIE 상태라 카드를 받을수 있는 기회가 주어지지 않습니다, score=${player.cards.totalScore()}")
//            }
        }
    }

    fun results(dealer: Dealer): Map<Player, PlayableResult> {
        return this.values.map { player -> player to player.result(dealer) }.toMap()
    }
}
