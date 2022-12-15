package blackjack.dto

import blackjack.domain.player.Player

@JvmInline
value class PlayerDto(private val player: Player) {
    fun getName(): String {
        return player.name.toString()
    }

    fun getCards(): String {
        return player.cards.toString()
    }

    fun getScore(): Int {
        return player.getScore()
    }
}
