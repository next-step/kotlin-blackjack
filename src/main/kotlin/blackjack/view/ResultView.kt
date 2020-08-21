package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Game.Companion.DEFAULT_CARD_COUNT
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.YES
import blackjack.domain.NO

const val DEALER_GETS_ONE_MORE_CARD = "딜러는 합계가 16이하라 한장의 카드를 더 받았습니다."
const val DEALER_GETS_NO_CARD = "딜러는 합계가 17이상이라 카드를 더 받지 않았습니다."
const val TITLE_OF_MATCH_RESULT = "## 최종 수익"

object ResultView {

    fun showResultOfSetUp(dealer: Dealer, players: Players) {
        println("\n${dealer}와 ${players}에게 2장의 카드를 나누었습니다")
        println("$dealer: ${dealer.stateOfCards().substringAfter(",")}")
        println(players.statesOfCards() + "\n")
    }

    fun showCardsDetail(player: Player, reply: String) {
        if (YES == reply) {
            println("${player}카드: ${player.stateOfCards()}")
        }
        if (NO == reply && player.cardCount() == DEFAULT_CARD_COUNT) {
            println("${player}카드: ${player.stateOfCards()}")
        }
    }

    fun showPlayOfDealer(dealer: Dealer) {
        if (dealer.cardCount() != DEFAULT_CARD_COUNT) {
            println("\n$DEALER_GETS_ONE_MORE_CARD")
            return
        }

        println("\n$DEALER_GETS_NO_CARD")
    }

    fun showScoreResult(dealer: Dealer, players: Players) {
        println("\n$dealer 카드: ${dealer.stateOfCards()} - 결과: ${dealer.score()}")
        val scoreResult =
            (0 until players.size()).map { players.findPlayer(it) }
                .joinToString("\n") { "${it}카드: ${it.stateOfCards()} - 결과: ${it.score()}" }

        println(scoreResult)
    }

    fun showMatchResult(dealer: Dealer, players: Players) {
        println("\n$TITLE_OF_MATCH_RESULT")

        println("$dealer: ${dealer.result}")

        val resultOfPlayers =
            (0 until players.size()).map { players.findPlayer(it) }
                .joinToString("\n") { "$it: ${it.profit}" }
        println(resultOfPlayers)
    }
}
