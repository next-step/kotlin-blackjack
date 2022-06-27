package blackjack.view

import blackjack.dto.PlayerDto
import blackjack.dto.UserDto

object PlayerView {

    private val cardsTemplate = { player: PlayerDto -> "${player.name} 카드 : ${player.cards.joinToString()}" }

    private const val CAN_NOT_DRAW_CARD_MESSAGE = "는 21점 이상이라 카드를 받을 수 없습니다."

    private const val MORE_CARD_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    fun printMoreCard(player: UserDto) {
        println("${player.name}$MORE_CARD_MESSAGE")
    }

    fun printCards(player: PlayerDto) {
        println(cardsTemplate(player))
    }

    fun printCardsWithResult(player: PlayerDto) {
        println("${cardsTemplate(player)} - 결과: ${player.score}")
    }

    fun printCanNotDrawCard(player: UserDto) {
        println("${player.name}$CAN_NOT_DRAW_CARD_MESSAGE")
    }
}
