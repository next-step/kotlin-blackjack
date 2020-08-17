package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Game.Companion.DEFAULT_CARD_AMOUNT
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.REPLY_NO
import blackjack.domain.REPLY_YES

const val NOTIFY_EMPTY_DECK = "-- 더 이상 남아있는 카드가 없습니다 --"
const val DEALER_GETS_ONE_MORE_CARD = "딜러는 합계가 16이하라 한장의 카드를 더 받았습니다."
const val DEALER_GETS_NO_CARD = "딜러는 합계가 17이상이라 카드를 더 받지 않았습니다."
const val TITLE_OF_MATCH_RESULT = "## 최종 승패"

object ResultView {

    fun showResultOfSetUp(dealer: Dealer, players: Players) {
        println("\n${dealer}와 ${players}에게 2장의 카드를 나누었습니다")
        println("$dealer: ${dealer.faceUpCard()}")
        println(players.statesOfCards() + "\n")
    }

    fun showStateOfCards(player: Player?, reply: String): Boolean {
        if (player == null) return true

        if (REPLY_YES == reply) {
            println("${player}카드: ${player.stateOfCards()}")
        }
        if (REPLY_NO == reply && player.countOfCards() == DEFAULT_CARD_AMOUNT) {
            println("${player}카드: ${player.stateOfCards()}")
        }
        return false
    }

    fun showPlayOfDealer(dealer: Dealer?) {
        if (dealer == null) {
            println("\n$NOTIFY_EMPTY_DECK")
            return
        }

        if (dealer.countOfCards() != DEFAULT_CARD_AMOUNT) {
            println("\n$DEALER_GETS_ONE_MORE_CARD")
            return
        }

        println("\n$DEALER_GETS_NO_CARD")
    }

    fun showScoreResult(dealer: Dealer, players: Players) {
        println("\n$dealer 카드: ${dealer.stateOfCards()} - 결과: ${dealer.totalScore()}")
        val scoreResult =
            (0 until players.size()).map { players.findPlayer(it) }
                .joinToString("\n") { "${it}카드: ${it.stateOfCards()} - 결과: ${it.totalScore()}" }

        println(scoreResult)
    }

    fun showMatchResult(result: Pair<Dealer, Players>) {
        println("\n$TITLE_OF_MATCH_RESULT")
        val dealer = result.first
        val players = result.second

        println("$dealer: ${dealer.result[0]}승 ${dealer.result[1]}패")

        val resultOfPlayers =
            (0 until players.size()).map { players.findPlayer(it) }
                .joinToString("\n") { "$it: ${it.matchResult}" }
        println(resultOfPlayers)
    }
}
