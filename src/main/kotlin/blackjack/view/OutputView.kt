package blackjack.view

import blackjack.domain.deck.Card
import blackjack.domain.player.Player

object OutputView {
    fun showStartStatus(players: List<Player>) {
        println("${players.joinToString(", ") { it.name }}에게 2장씩 나누었습니다.")
        for (player in players) {
            showPlayerStatus(player)
        }
        println()
    }

    fun showPlayerStatus(player: Player) {
        println("${player.name}카드: ${showCards(player.status.cards.cards)}")
    }

    private fun showCards(cards: List<Card>): String {
        return cards.joinToString(", ") { "${it.denomination.denomination}${it.suit.koreanName}" }
    }

    fun showResult(players: List<Player>) {
        for (player in players) {
            showPlayerResult(player)
        }
    }

    private fun showPlayerResult(player: Player) {
        println("${player.name}카드: ${showCards(player.status.cards.cards)} - 결과: ${player.score()}")
    }
}
