package blackjack.dto

import blackjack.domain.Card
import blackjack.domain.Stat
import blackjack.domain.user.Player

data class PlayerDto(val name: String, val cards: List<Card>, val score: Int, val stat: Stat) {
    companion object {
        fun of(player: Player, stat: Stat): PlayerDto {
            return PlayerDto(
                name = player.name,
                cards = player.cards(),
                score = player.getScore().value,
                stat = stat
            )
        }
    }
}
