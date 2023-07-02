package blackjack.view

import blackjack.domain.game.Game
import blackjack.domain.player.Gamer
import blackjack.domain.player.Gamers

object OutputView {

    fun printInputGameParticipant() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    fun printDivideIntoTwoPieces(gamers: Gamers) {
        val names = gamers.gamers.map { it.name }
        val result = names.joinToString(", ")
        println("\n${result}에게 ${Game.INIT_CARD_COUNT}장을 나누었습니다.")
    }

    fun printCardsGamerHas(gamer: Gamer) {
        val deck = gamer.deck.getCards().joinToString(", ")
        println("${gamer.name}카드: $deck")
    }

    fun printOneMoreCard(gamer: Gamer) {
        println("${gamer.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun printMessage(message: String) {
        println(message)
    }

    fun printGameResult(gamer: Gamer) {
        val deck = gamer.deck.getCards().joinToString(", ")
        println("${gamer.name}카드: $deck - 결과: ${gamer.deck.getScore()}")
    }
}
