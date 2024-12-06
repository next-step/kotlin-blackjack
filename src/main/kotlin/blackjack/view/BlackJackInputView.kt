package blackjack.view

import blackjack.domain.BlackJackDealer
import blackjack.domain.BlackJackNormalPlayer
import blackjack.domain.BlackJackPlayer

object BlackJackInputView {
    fun getPlayerName(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",")
    }

    fun drawBlackJackPlayersCards(
        blackJackNormalPlayers: List<BlackJackNormalPlayer>,
        blackJackDealer: BlackJackDealer,
    ) {
        blackJackNormalPlayers.forEach {
            drawBlackJackPlayerCards(it)
        }
        drawBlackJackPlayerCards(blackJackDealer)
    }

    fun getPlayerDrawCardYn(blackJackNormalPlayer: BlackJackNormalPlayer): Boolean {
        println("${blackJackNormalPlayer.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val yn = readln()
        return yn == "y"
    }

    fun drawBlackJackPlayerCards(blackJackPlayer: BlackJackPlayer) {
        print("${blackJackPlayer.name}카드: ")
        println(blackJackPlayer.blackJackPlayerCards.cards.map { "${it.number.name} ${it.shape.name}" }.joinToString(","))
    }

    fun getPlayerBets(playerNames: List<String>): List<Int> {
        val result = mutableListOf<Int>()
        playerNames.forEach {
            println("${it}의 배팅 금액은?")
            result.add(readln().toInt())
        }
        return result.toList()
    }
}
