package blackjack.ui

import blackjack.Player

class ConsoleOutput {
    companion object {
        fun printShareInitialCardsToPlayers(playerNames: List<String>) {
            println()
            println("${playerNames.joinToString(", ")}에게 2장의 나누었습니다.")
        }

        fun printAllPlayersWithNameAndHand(players: List<Player>) {
            players.forEach { player ->
                printPlayerWithNameAndHand(player)
            }
            println()
        }

        fun printPlayerWithNameAndHand(player: Player) {
            printPlayerNameAndPlayerHand(player)
            println()
        }

        fun printAllPlayersWithNameAndHandAndResult(players: List<Player>) {
            println()
            players.forEach { player ->
                printPlayerWithNameAndHandAndResult(player)
            }
        }

        private fun printPlayerWithNameAndHandAndResult(player: Player) {
            printPlayerNameAndPlayerHand(player)
            println(" - 결과: ${player.sumOfHand()}")
        }

        private fun printPlayerNameAndPlayerHand(player: Player) {
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
