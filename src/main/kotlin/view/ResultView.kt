package view

import model.CardNumberCalculator
import model.Dealer
import model.GameResultState
import model.Names
import model.Participant

object ResultView {
    fun showDistributedCard(names: Names) {
        var players = ""
        for ((index, name) in names.values.withIndex()) {
            if (index == 0) {
                players += name
                continue
            }
            players += ", $name"
        }
        println("딜러와 ${players}에게 2장의 카드를 나누었습니다.")
    }

    fun showPlayersCardState(players: List<Participant>) {
        players.forEach { player ->
            showPlayerCardState(player)
        }
    }

    fun showPlayerCardState(player: Participant) {
        var cards = ""
        cards += "${player.name}카드: ${player.cards.joinToString()} ,"
        cards = cards.substring(0, cards.lastIndex - 1)
        println(cards)
    }

    fun showPlayersCardStateResult(players: List<Participant>) {
        players.forEach { player ->
            showPlayerCardStateResult(player)
        }
    }

    fun showPlayerCardStateResult(player: Participant) {
        var playerCardState = "${player.name}카드: ${player.cards.joinToString()} ,"
        playerCardState = playerCardState.substring(
            0,
            playerCardState.lastIndex - 1
        ) + " - 결과: ${CardNumberCalculator.sumCardNumbers(player.cards)}"
        println(playerCardState)
    }

    fun showGameResult(dealer: Dealer, players: List<Participant>) {
        println("\n## 최종 승패")
        showDealerGameResult(dealer, players)
        players.forEach { player ->
            showPlayerGameResult(player)
        }
    }

    private fun showDealerGameResult(dealer: Participant, players: List<Participant>) {
        var win = 0
        var lose = 0
        var draw = 0
        players.forEach { player ->
            when (player.gameResultState) {
                GameResultState.WIN -> lose++
                GameResultState.LOSE -> win++
                GameResultState.DRAW -> draw++
            }
        }
        println("${dealer.name}: ${win}승 ${lose}패 ${draw}무")
    }

    private fun showPlayerGameResult(player: Participant) {
        val gameResult = "${player.name}: ${player.gameResultState.text}"
        println(gameResult)
    }

    fun showReceivedCardByDealer() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
    }
}
