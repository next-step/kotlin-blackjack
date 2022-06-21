package blackjack.dto

import blackjack.domain.Card
import blackjack.domain.Player
import blackjack.domain.Stat

data class PlayerDto(val name: String, val cards: List<Card>, val point: Int, val stat: Stat) {
    companion object {
        fun of(player: Player, stat: Stat): PlayerDto {
            return PlayerDto(
                name = player.name,
                cards = player.cards.toList(),
                point = player.getPoints(),
                stat = stat
            )
        }
    }
}
