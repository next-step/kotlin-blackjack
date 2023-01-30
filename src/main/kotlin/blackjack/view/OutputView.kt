package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Draw
import blackjack.domain.Game
import blackjack.domain.Gamer
import blackjack.domain.Person

object OutputView {

    fun printParticipantInfo(game: Game) {
        println()
        val participantNames = game.getParticipantNames().joinToString(",")
        println("${participantNames}에게 ${Draw.FIRST_DRAW_COUNT}장의 카드를 나누었습니다.")
        game.participant.forEach {
            printOwnCards(it)
            println()
        }
    }

    fun printOwnCards(person: Person) {
        print("${person.name}카드: ${person.ownCards.cards.joinToString(", ") { it.cardNumber.display + it.pattern.display }}")
    }

    fun printDealerCardAddYn(game: Game): Boolean {
        println()
        return if (game.dealer.isDrawable) {
            println()
            println("딜러는 ${Dealer.LEAST_CARD_SUM}이하라 한장의 카드를 더 받았습니다.")
            true
        } else {
            false
        }
    }

    fun printCardInfo(game: Game) {
        println()
        game.participant.forEach {
            printOwnCards(it)
            println(" - 결과: ${it.ownCards.sumCardNumber()}")
        }
    }

    fun printResult(game: Game) {
        println()
        println("## 최종 수익")
        val dealer: Dealer = game.dealer
        val gamer: List<Gamer> = game.gamerList
        println("${dealer.name}: ${dealer.money}")
        gamer.forEach { println("${it.name}: ${it.money}") }
    }
}
