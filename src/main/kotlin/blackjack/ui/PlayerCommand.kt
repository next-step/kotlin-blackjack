package blackjack.ui

import blackjack.domain.Player

data class PlayerCommand(
    val isHit: Boolean,
    val player: Player,
) {
    companion object {
        fun of(
            command: String,
            player: Player,
        ): PlayerCommand =
            when (command.lowercase()) {
                "y" -> PlayerCommand(true, player)
                "n" -> PlayerCommand(false, player)
                else -> throw IllegalArgumentException("예는 y, 아니오는 n으로 입력해주세요.")
            }
    }
}
