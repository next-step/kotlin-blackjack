package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.GameManager
import blackjack.domain.Player
import blackjack.view.askForDraw
import blackjack.view.inputNames
import blackjack.view.printInitialSupply
import blackjack.view.printUserCardInfo

fun main() {
    // 참가자 명단 입력
    val players = inputNames().map { Player(it) }

    // 딜러 생성, 초기카드 분배
    val dealer = Dealer()
    repeat(GameManager.INITIAL_CARD_COUNT) {
        players.forEach { dealer.supplyCard(it) }
    }

    // 초기 카드 분배결과 출력
    printInitialSupply(players, GameManager.INITIAL_CARD_COUNT)
    players.forEach { printUserCardInfo(it) }

    // 각 사용자의 추가 draw 진행
    players.forEach { drawWhileUserWants(it, dealer) }
}

private fun drawWhileUserWants(player: Player, dealer: Dealer) {
    var isPrinted = false

    while (askForDraw(player.name)) {
        isPrinted = true

        dealer.supplyCard(player)
        printUserCardInfo(player)
    }

    if (!isPrinted) {
        printUserCardInfo(player)
    }
}
