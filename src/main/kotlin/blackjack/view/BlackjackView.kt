package blackjack.view

import blackjack.dto.BlackjackDto
import blackjack.dto.PlayerDto

object BlackjackView {

    fun printPlayerInput() {
        println(PLAYER_INPUT_MESSAGE)
    }

    fun printInitialize(players: List<PlayerDto>) {
        println("${players.joinToString(", ") { it.name }}$BLACKJACK_INIT_MESSAGE")
    }

    fun printCards(blackjack: BlackjackDto) {
        blackjack.players.forEach { PlayerView.printCards(it) }
    }

    fun printResult(blackjack: BlackjackDto) {
        println()
        DealerView.printCardsWithResult(blackjack.dealer)
        blackjack.players.map { PlayerView.printCardsWithResult(it) }
    }

    private const val PLAYER_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val BLACKJACK_INIT_MESSAGE = "에게 2장의 카드를 나누었습니다."
}
