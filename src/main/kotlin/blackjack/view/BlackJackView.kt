package blackjack.view

import blackjack.domain.BlackJackPlayer
import blackjack.domain.BlackJackPlayers
import blackjack.domain.BlackJackResult

object BlackJackView {
    fun getPlayerName(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",")
    }

    fun drawBlackJackPlayersCards(blackJackPlayers: BlackJackPlayers) {
        blackJackPlayers.players.forEach {
            drawBlackJackPlayerCards(it)
        }
    }

    fun getPlayerDrawCardYn(blackJackPlayer: BlackJackPlayer): Boolean {
        println("${blackJackPlayer.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val yn = readln()
        return yn == "y"
    }

    fun drawBlackJackPlayerCards(blackJackPlayer: BlackJackPlayer) {
        print("${blackJackPlayer.name}카드: ")
        println(blackJackPlayer.blackJackPlayerCards.cards.map { "${it.number.name} ${it.shape.name}" }.joinToString(","))
    }

    fun drawBlackJackPlayersCardsWithResult(blackJackPlayers: BlackJackPlayers) {
        blackJackPlayers.players.forEach {
            drawBlackJackPlayerCards(it)
            println(" 결과: ${it.getBestSum()}")
        }
    }

    fun drawWinPlayer(blackJackResult: BlackJackResult) {
        println("승리자는 ")
        print(blackJackResult.getWinPlayers().map { it.name }.joinToString(","))
    }
}
