package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.Card.AceCard
import blackjack.domain.card.Card.BasicCard
import blackjack.domain.card.Card.JackCard
import blackjack.domain.card.Card.KingCard
import blackjack.domain.card.Card.QueenCard
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

class ResultView {

    fun printInitDistributed(players: List<Player>) {
        println("${players.joinToString(", ") { it.name }} 에게 2장을 나누었습니다.")
        printCardsByPlayers(players, false)
    }

    fun printCardsByPlayers(player: List<Player>, withScore: Boolean) {
        println("")
        player.map {
            printCardsByPlayer(it, withScore)
        }
    }

    fun printCardsByPlayer(player: Player, withScore: Boolean) {
        if (withScore) {
            println("${player.name}카드: ${player.receivedCards.getCardDescription()} - 결과: ${player.score}")
            return
        }

        println("${player.name}카드: ${player.receivedCards.getCardDescription()}")
    }

    fun printFinalResult(players: List<Player>) {
        val dealer = players
            .filterIsInstance<Dealer>()
            .first()

        println("${dealer.name}: ${dealer.win}승 ${dealer.lose}패")

        players.filter { it !is Dealer }.map {
            println("${it.name}: ${convertWinOrLose((it.isWinner))}")
        }
    }

    private fun convertWinOrLose(toConvert: Boolean): String {
        return if (toConvert) "승" else "패"
    }
}
