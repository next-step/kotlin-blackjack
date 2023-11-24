package blackjack.controller

import blackjack.domain.BlackjackUtil.INITIAL_CARD_NUM
import blackjack.domain.BlackjackUtil.isBust
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.view.askForDraw
import blackjack.view.inputNames
import blackjack.view.printInitialSupply
import blackjack.view.printResult
import blackjack.view.printUserCardInfo

fun main() {
    // 딜러 생성
    val dealer = Dealer()

    // 참가자 명단 입력
    val players = listOf(dealer) + inputNames().map { Player(it) }

    // 초기 카드 분배
    repeat(INITIAL_CARD_NUM) {
        players.forEach { dealer.supplyCard(it) }
    }

    // 초기 카드 분배결과 출력
    printInitialSupply(players, INITIAL_CARD_NUM)
    players.forEach { printUserCardInfo(it, true) }

    // 각 사용자의 추가 draw 진행
    players.forEach { drawWhileUserWants(it, dealer) }

    // 결과 출력
    printResult(players)
}

private fun drawWhileUserWants(player: Player, dealer: Dealer) {
    var isPrinted = false

    // 점수 합계가 21을 넘지 않는다면 추가 draw 가능
    while (!isBust(player.hand.sumOf()) && askForDraw(player.name)) {
        isPrinted = true

        dealer.supplyCard(player)
        printUserCardInfo(player)
    }

    // 추가 draw로 인한 현황 출력이 없었다면 한번 출력
    if (!isPrinted) {
        printUserCardInfo(player)
    }
}
