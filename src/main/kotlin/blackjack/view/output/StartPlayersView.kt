package blackjack.view.output

import blackjack.view.dto.PlayersDto

object StartPlayersView {
    fun print(dto: PlayersDto) {
        println()
        println("${dto.players.joinToString(", ") { player -> player.name} }에게 2장의 카드를 나누었습니다.")
        dto.players.forEach {
            println("${it.name}카드: ${it.cards.joinToString(", ") { card -> "${card.shape}${card.number}" }}")
        }
    }
}
