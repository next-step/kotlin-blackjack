package blackjack.view

import blackjack.domain.deck.Card
import blackjack.domain.game.Game
import blackjack.domain.player.Player

object OutputView {
    fun showStartStatus(game: Game) {
        println("${showPlayers(game.players)}에게 2장씩 나누었습니다.")
        for (player in game.players) {
            showPlayer(player)
        }
    }

    fun showPlayer(player: Player) {
        println("${player.name}카드 : ${showCards(player.cards)}")
    }

    private fun showPlayers(players: List<Player>): String {
        return players.joinToString(", ") { it.name }
    }

    fun showBlackJack(players: List<Player>) {
        println("블랙잭 발생!\n")

        for (player in players) {
            showPlayerResult(player)
        }
    }

    fun showResult(players: List<Player>) {
        println("게임 결과\n")

        for (player in players) {
            showPlayerResult(player)
        }
    }

    private fun showPlayerResult(player: Player) {
        println("${player.name}카드 : ${showCards(player.cards)} - 결과: ${player.score()}")
    }

    private fun showCards(cards: List<Card>): String {
        return cards.joinToString(", ") { "${it.denomination.denomination}${it.suit.koreanName}" }
    }
}
