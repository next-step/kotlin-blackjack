package blackjack.view

import blackjack.domain.deck.Card
import blackjack.domain.game.Game
import blackjack.domain.game.GameResult
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

object OutputView {
    fun showStartStatus(game: Game) {
        println("딜러, ${showPlayers(game.playersInGame + game.playersOutOfGame)}에게 2장씩 나누었습니다.")
        showPlayer(game.dealer.player)
        for (player in game.playersInGame) {
            showPlayer(player)
        }
    }

    fun showPlayer(player: Player) {
        println("${player.name}카드 : ${showCards(player.cards)} \n")
    }

    private fun showPlayers(players: List<Player>): String {
        return players.joinToString(", ") { it.name }
    }

    fun showResult(game: Game) {
        showPlayerResult(game.dealer.player)
        for (player in game.playersOutOfGame) {
            showPlayerResult(player)
        }
        val gameResult = GameResult(game)

        println()
        println("## 최종 승패")
        println("딜러: ${gameResult.getDealerProfit()}")
        for (player in game.playersOutOfGame) {
            println("${player.name}: ${gameResult.calculatePlayerProfitFor(player)}")
        }
    }

    private fun showPlayerResult(player: Player) {
        println("${player.name}카드 : ${showCards(player.cards)} - 결과: ${player.score()}")
    }

    private fun showCards(cards: List<Card>): String {
        return cards.joinToString(", ") { "${it.denomination.denomination}${it.suit.koreanName}" }
    }

    fun notifyDealerDraw() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
    }
}
