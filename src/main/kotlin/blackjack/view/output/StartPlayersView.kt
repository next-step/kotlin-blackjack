package blackjack.view.output

import blackjack.domain.player.Dealer
import blackjack.view.dto.ParticipantsDto

object StartPlayersView {
    fun print(
        dealer: Dealer,
        dto: ParticipantsDto,
    ) {
        println()
        println("${dto.players.joinToString(", ") { player -> player.name }}에게 2장의 카드를 나누었습니다.")
        dto.players.forEach {
            if (it.name == dealer.name) {
                println("${it.name}: ${it.cards[0].shape}${it.cards[0].number}")
            } else {
                println("${it.name}카드: ${it.cards.joinToString(", ") { card -> "${card.shape}${card.number}" }}")
            }
        }
        println()
    }
}
