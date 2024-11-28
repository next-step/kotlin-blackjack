package blackjack.view.output

import blackjack.view.dto.PlayersDto

object ResultView {
    fun print(dto: PlayersDto) {
        println()
        dto.players.forEach { player ->
            println(
                "${player.name}카드: ${player.cards.joinToString(", ")
                    { card -> "${card.shape}${card.number}" }} - 결과: ${player.cards.sumOf { it.number }}",
            )
        }
    }
}
