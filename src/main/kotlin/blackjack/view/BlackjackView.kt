package blackjack.view

import blackjack.domain.Player

class BlackjackView {
    fun printInitialTurn(names: List<String>, initialDraw: Int) {
        println("\n${names.joinToString(", ")}에게 ${initialDraw}장을 나누었습니다.")
    }

    fun printPlayerCard(player: Player) {
        print("${player.name}카드: ")
        player.showMyCards()
    }
}
