package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Game
import blackjack.view.Screen
import blackjack.view.Screen.displayGameResults
import blackjack.view.Screen.displayCardScores

fun main() {
    val dealer = Dealer()
    val game = Game(dealer)
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    val players = game.enter(readln())

    game.shareCards(listOf(dealer) + players)
    Screen.displayPlayerCards(players, dealer)

    players.map { player -> game.shareMoreCards(player) }
    dealer.getMoreCard()

    displayCardScores(listOf(dealer) + players)
    displayGameResults(players, dealer)
}
