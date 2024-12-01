package blackjack.view.output

import blackjack.view.dto.ParticipantDto

object PlayerView {
    fun print(dto: ParticipantDto) {
        println("${dto.name}카드: ${dto.cards.joinToString(", ") { card -> "${card.shape}${card.number}" }}")
    }
}
