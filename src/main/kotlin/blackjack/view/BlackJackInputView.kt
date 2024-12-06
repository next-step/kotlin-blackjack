package blackjack.view

import blackjack.domain.BlackJackDealer
import blackjack.domain.BlackJackNormalPlayer

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
            drawBlackJackNormalPlayerCards(it)
        }
        drawBlackJackDealerCards(blackJackDealer)
    }

    fun getPlayerDrawCardYn(blackJackNormalPlayer: BlackJackNormalPlayer): Boolean {
        println("${blackJackNormalPlayer.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val yn = readln()
        return yn == "y"
    }

    fun drawBlackJackNormalPlayerCards(blackJackNormalPlayer: BlackJackNormalPlayer) {
        print("${blackJackNormalPlayer.name}카드: ")
        println(blackJackNormalPlayer.blackJackPlayerCards.cards.map { "${it.number.name} ${it.shape.name}" }.joinToString(","))
    }

    fun drawBlackJackDealerCards(blackJackDealer: BlackJackDealer) {
        print("딜러 카드: ")
        println(blackJackDealer.blackJackPlayerCards.cards.map { "${it.number.name} ${it.shape.name}" }.joinToString(","))
    }
}
