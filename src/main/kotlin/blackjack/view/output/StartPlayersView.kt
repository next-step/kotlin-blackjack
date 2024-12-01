package blackjack.view.output

import blackjack.view.dto.ParticipantsDto

object StartPlayersView {
    fun print(dto: ParticipantsDto) {
        println()
        println("${dto.players.joinToString(", ") { player -> player.name }}에게 2장의 카드를 나누었습니다.")
        println("${dto.dealer.name}: ${dto.dealer.cards[0].shape}${dto.dealer.cards[0].number}")
        dto.players.forEach {
            println("${it.name}카드: ${it.cards.joinToString(", ") { card -> "${card.shape}${card.number}" }}")
        }
        println()
    }
}
