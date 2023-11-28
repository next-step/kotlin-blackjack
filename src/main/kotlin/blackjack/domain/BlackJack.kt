package blackjack.domain

import blackjack.view.InputView
import blackjack.view.ResultView

fun main(){
    //카드 묶음 52장을 준비한다.
    val deck = Deck.init()
    // 게임에 참여할 사람의 이름을 입력받는다.
    val players = InputView.getPlayers()
    // 게임에 참여하는 사람에게 각각 카드를 2장씩 부여한다.
    ResultView.firstDealCard(players)

    // player 첫번째 사람부터 카드를 줄것인지 물어본다 play가 시작
    /**
     * player별로 한명씩 카드를 받을 것인지 물어본다.
     *
     * */
    for (player in players) {
        val cars = deck.firstDraw() // 한장 뽑는다 .
        cars.forEach {
            player.cards.addCard(it)
        }
        ResultView.showPlayerCards(player)
    }

    for (player in players) {
        player.play(deck)
    }


    for (player in players) {
        ResultView.printResult(player)
    }


}
private fun Player.play(deck: Deck) {
    if (InputView.askPlayer(name)) {
        this.hit(deck.draw())
        ResultView.showPlayerCards(this)
    }
}
