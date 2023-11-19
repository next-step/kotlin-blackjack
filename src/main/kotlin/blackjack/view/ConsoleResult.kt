package blackjack.view

import blackjack.domain.Player

class ConsoleResult {
    companion object {
        fun drawAllFirstTwoCards(players: List<Player>) {
            println()
            println("${players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
        }

        fun printCardsOfPlayers(players: List<Player>) {
            players.forEach { printCardsOfPlayer(it) }
        }

        fun printCardsOfPlayer(player: Player) {
            println("${player.name}카드: ${player.cards.joinToString { card -> card.character.mark + card.shape.korean }}")
        }

        fun printCardsAndTotalScoreOfPlayers(players: List<Player>) {
            println()
            players.forEach {
                println("${it.name}카드: ${it.cards.joinToString { card -> card.character.mark + card.shape.korean }} - 결과: ${it.totalScore}")
            }
        }

        fun notifyPlayerCannotDraw(player: Player) {
            println("${player.name}는 총 점수가 21점이 넘어 더이상 카드를 받을 수 없습니다.")
        }
    }
}
