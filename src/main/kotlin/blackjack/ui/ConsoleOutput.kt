package blackjack.ui

import blackjack.Player

class ConsoleOutput {
    companion object {
        fun printShareInitialCardsToPlayers(playerNames: List<String>) {
            println("${playerNames.joinToString(", ")}에게 2장의 나누었습니다.")
        }

        fun printPlayerNameWithHand(player: Player) {
            print("${player.name}카드: ")
            print(player.hand.joinToString { "${it.number.face}${it.suit.koreanName}" })
        }

        fun printPlayerSumOfHand(player: Player) {
            println(" - 결과: ${player.sumOfHand()}")
        }

        fun printPlayerBust(player: Player) {
            print("${player.name}는 파산했습니다!")
        }
    }
}
