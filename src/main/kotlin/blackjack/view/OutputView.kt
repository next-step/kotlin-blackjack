package blackjack.view

import blackjack.Card
import blackjack.Player

class OutputView {
    companion object {
        fun printFirstDeal(players: String) {
            println("$players 에게 2장의 카드를 나누었습니다.")
        }

        fun printCardsByPlayer(playerDtos: List<PlayerDto>) {
            playerDtos.forEach { printCardsByPlayer(it) }
        }

        fun printCardsByPlayer(playerDto: PlayerDto) {
            print("${playerDto.name}카드: ")
            println(playerDto.cards.joinToString(", ") { it.korean })
        }

        fun printResult(playerDtos: List<PlayerDto>) {
            playerDtos.forEach { printResultByPlayer(it) }
        }

        private fun printResultByPlayer(playerDto: PlayerDto) {
            print("${playerDto.name}카드: ")
            print(playerDto.cards.joinToString(", ") { it.korean })
            println(" - 결과: ${playerDto.totalPoint}")
        }
    }
}

data class PlayerDto(
    val name: String,
    val cards: Set<Card>,
    val totalPoint: Int,
) {
    constructor(player: Player) : this(player.name, player.playingCards.cards, player.playingCards.calculatePoint())
}
