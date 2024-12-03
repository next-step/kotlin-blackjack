package blackjack.view.output

import blackjack.view.dto.ParticipantsDto

object ResultView {
    fun print(dto: ParticipantsDto) {
        println()
        (dto.players + listOf(dto.dealer)).forEach { player ->
            println(
                "${player.name}카드: ${player.cards.joinToString(", ")
                    { card -> "${card.shape}${card.number}" }} - 결과: ${player.cards.sumOf { it.number }}",
            )
        }
        println()
    }
}
