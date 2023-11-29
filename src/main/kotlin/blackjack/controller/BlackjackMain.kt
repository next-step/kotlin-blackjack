package blackjack.controller

import blackjack.domain.BettingBoard
import blackjack.domain.Dealer
import blackjack.domain.Dealer.Companion.INITIAL_CARD_NUM
import blackjack.domain.Player
import blackjack.view.askForDraw
import blackjack.view.inputBet
import blackjack.view.inputNames
import blackjack.view.printDealerDrawsMore
import blackjack.view.printInitialSupply
import blackjack.view.printResults
import blackjack.view.printScores
import blackjack.view.printUserCardInfo

fun main() {
    // 딜러 생성
    val dealer = Dealer()

    // 참가자 명단 입력
    val players = listOf(dealer) + inputNames().map { Player(it) }

    // 각 사용자의 베팅 입력
    val bettingBoard = players.filter { it !is Dealer }
        .associate { it.name to inputBet(it.name) }
        .let { BettingBoard(it) }

    // 초기 카드 분배
    dealer.supplyInitialCards(players)

    // 초기 카드 분배결과 출력
    printInitialSupply(players, INITIAL_CARD_NUM)
    players.forEach { printUserCardInfo(it.name, it.initialCards()) }

    // 각 사용자의 추가 draw 진행
    players.filter { it !is Dealer }
        .forEach { drawWhileUserWants(it, dealer) }

    // 딜러의 추가 draw 진행
    drawForDealer(dealer)

    // 최종점수 계산
    players.forEach { it.setFinalScore() }

    // 점수 출력
    printScores(players)

    // 플레이어 승패 계산
    players.filter { it !is Dealer }
        .forEach { it.setResult(dealer.getFinalScore()) }

    // 승자 출력
    printResults(dealer, players.filter { it !is Dealer })
}

private fun drawForDealer(dealer: Dealer) {
    while (dealer.canDrawMore()) {
        dealer.supplyCard(dealer)
        printDealerDrawsMore()
    }
}

private fun drawWhileUserWants(player: Player, dealer: Dealer) {
    var isPrinted = false

    // 점수 합계가 21을 넘지 않는다면 추가 draw 가능
    while (player.canDrawMore() && askForDraw(player.name)) {
        isPrinted = true

        dealer.supplyCard(player)
        printUserCardInfo(player.name, player.hand.toList())
    }

    // 추가 draw로 인한 현황 출력이 없었다면 한번 출력
    if (!isPrinted) {
        printUserCardInfo(player.name, player.hand.toList())
    }
}
