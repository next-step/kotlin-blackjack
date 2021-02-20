package blackjack.view

import blackjack.domain.deck.Card
import blackjack.domain.game.Game
import blackjack.domain.game.GameResult
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

object OutputView {
    fun showStartStatus(game: Game) {
        println("딜러, ${showPlayers(game.playersInGame)}에게 2장씩 나누었습니다.")
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

    fun showBlackJack(players: List<Player>) {
        println("블랙잭 발생!\n")

        for (player in players) {
            showPlayerResult(player)
        }
    }

    fun showResult(dealer: Dealer, players: List<Player>) {
        showPlayerResult(dealer.player)
        for (player in players) {
            showPlayerResult(player)
        }

        println()
        println("## 최종 승패")
        println("딜러: ${GameResult.getDealerWinCounts(dealer, players)}승 ${GameResult.getDealerLoseCounts(dealer, players)}패")
        for (player in players) {
            println("${player.name}: ${getPlayerWin(dealer, player)}")
        }
    }

    private fun getPlayerWin(dealer: Dealer, player: Player): String {
        if( GameResult.getPlayerResult(dealer, player)) {
            return "승"
        }
        return "패"
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
