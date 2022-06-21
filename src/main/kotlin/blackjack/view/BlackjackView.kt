package blackjack.view

import blackjack.domain.Blackjack
import blackjack.domain.Player

object BlackjackView {

    fun printPlayerInput() {
        println(PLAYER_INPUT_MESSAGE)
    }

    fun printInitialize(players: List<Player>) {
        println("${players.joinToString(", ") { it.name }}$BLACKJACK_INIT_MESSAGE")
    }

    fun printCards(blackjack: Blackjack) {
        DealerView.printFirstCard(blackjack.dealer)
        blackjack.players.forEach { PlayerView.printCards(it) }
    }

    fun printCanNotDrawCard(player: Player) {
        println("${player.name}${CAN_NOT_DRAW_CARD_MESSAGE}")
    }

    fun printMoreCard(player: Player) {
        println("${player.name}$MORE_CARD_MESSAGE")
    }

    fun printResult(blackjack: Blackjack) {
        println()
        PlayerView.printCardsWithResult(blackjack.dealer)
        blackjack.players.map { PlayerView.printCardsWithResult(it) }
    }

    private const val PLAYER_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val BLACKJACK_INIT_MESSAGE = "에게 2장의 카드를 나누었습니다."
    private const val CAN_NOT_DRAW_CARD_MESSAGE = "는 21점 이상이라 카드를 받을 수 없습니다."
    private const val MORE_CARD_MESSAGE = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
}
