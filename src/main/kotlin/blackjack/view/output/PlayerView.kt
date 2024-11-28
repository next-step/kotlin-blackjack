package blackjack.view.output

import blackjack.view.dto.PlayerDto

object PlayerView {
    fun print(dto: PlayerDto) {
        println("${dto.name}카드: ${dto.cards.joinToString(", ") { card -> "${card.shape}${card.number}" }}")
    }
}
