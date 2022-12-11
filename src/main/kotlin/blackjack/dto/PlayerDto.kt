package blackjack.dto

import blackjack.domain.player.Player

@JvmInline
value class PlayerDto(private val player: Player) {
    fun getName(): String {
        return player.name.value
    }

    fun getCards(): List<String> {
        return player.cards.cards()
    }
}
