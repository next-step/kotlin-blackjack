package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Players

object DisplayView {

    fun dealOutCards(players: Players) {
        val playersName = players.players.joinToString()
        println("딜러와 ${playersName}에게 2장의 카드 나누었습니다.")
    }

    fun cardsOfPlayers(dealer: Dealer, players: Players) {
        cardsOfDealer(dealer)
        players.players.forEach {
            cardsOfPlayer(it)
        }
    }

    fun cardsOfDealer(dealer: Dealer) {
        println("${dealer.name}: ${dealer.cards}")
    }

    fun cardsOfPlayer(player: Player) {
        println("${player.name}카드: ${player.cards}")
    }

    fun dealOutAdditionalCard(player: Player) {
        println("${player}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun dealOutAdditionalCard(dealer: Dealer) {
        if (dealer.getScore() > Dealer.LIMIT_SCORE) {
            println("딜러는 17이상이라 카드를 더 받지 않았습니다.")
        } else {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        }
    }

    fun finalScore(dealer: Dealer, players: Players) {
        printFinalScore(dealer)
        players.players.forEach { printFinalScore(it) }
    }

    private fun printFinalScore(player: Player) {
        println("${player.name}카드: ${player.cards} - 결과: ${player.getScore()}")
    }
}
