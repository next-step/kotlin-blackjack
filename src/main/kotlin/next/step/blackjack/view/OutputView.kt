package next.step.racing.view

import next.step.blackjack.domain.dealer.Dealer
import next.step.blackjack.domain.game.GameResults
import next.step.blackjack.domain.player.Player
import next.step.blackjack.domain.player.Players

object OutputView {

    private const val UNKNOWN_ERR_MSG = "알 수 없는 에러가 발생했습니다."

    fun showStart(dealer: Dealer, players: Set<Player>, cardCnt: Int) {
        println()
        showInitCardsCnt(dealer, players, cardCnt)
        showDealerFirstCard(dealer)
        showPlayersCards(players)
        println()
    }

    private fun showInitCardsCnt(dealer: Dealer, players: Set<Player>, cardCnt: Int) {
        val playerNames = players.joinToString(", ") { it.name() }
        println("${dealer.name()}와 ${playerNames}에게 ${cardCnt}장씩 나누었습니다.")
    }

    private fun showDealerFirstCard(dealer: Dealer) {
        println("${dealer.name()}: ${dealer.cardDescFirst()}")
    }

    private fun showPlayersCards(players: Set<Player>) {
        players.forEach {
            showPlayerCards(it)
        }
    }

    fun showPlayerCards(player: Player) {
        println(cardDescs(player))
    }

    private fun cardDescs(player: Player): String {
        val cardDescs = player.cardDescs().joinToString(", ")
        val playerCardsFormat = "${player.name()}카드: $cardDescs"
        return playerCardsFormat
    }

    fun showCardsWithPoints(dealer: Dealer, players: Set<Player>) {
        println()
        showDealerCardsWithPoints(dealer)
        showPlayersCardsWithPoints(players)
    }

    private fun showDealerCardsWithPoints(dealer: Dealer) {
        println("${dealer.name()} 카드: ${dealer.cardDescs().joinToString(", ")} - 결과: ${dealer.point()}")
    }

    private fun showPlayersCardsWithPoints(players: Set<Player>) {
        players.forEach {
            showPlayerCardsWithPoints(it)
        }
    }

    private fun showPlayerCardsWithPoints(player: Player) {
        println("${cardDescs(player)} - 결과: ${player.point()}")
    }

    fun showDealerHit(name: String) {
        println()
        println("${name}는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun showGameResult(dealerName: String, gameResults: GameResults) {
        println()
        println("## 최종 승패")
        showDealerGameResults(gameResults, dealerName)
        showPlayersGameResults(gameResults)
    }

    private fun showDealerGameResults(gameResults: GameResults, dealerName: String) {
        val dealerResult = gameResults.dealerGameResults.map { "${it.value}${it.key.desc}" }.joinToString(", ")
        println("$dealerName: $dealerResult")
    }

    private fun showPlayersGameResults(gameResults: GameResults) {
        gameResults.playersGameResult.forEach {
            println("${it.key}: ${it.value.desc}")
        }
    }

    fun showResult(dealer: Dealer, players: Players) {
        showCardsWithPoints(dealer, players)
        showGameResult(dealer.name(), dealer.fight(players))
    }

    fun showError(msg: String?) {
        println(msg ?: UNKNOWN_ERR_MSG)
        println()
    }
}
