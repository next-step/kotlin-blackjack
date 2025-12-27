package service

import consts.MIN_BETTING_AMOUNT
import domain.Dealer
import domain.Player
import presentation.InputView
import presentation.ResultView

class BlackjackGameService (
    private val cardDistributorService: CardDistributorService,
    private val blackjackResultCalculator: BlackjackResultCalculator,
) {
    fun run() {
        // 딜러 초기화
        val dealer = Dealer()

        // 플레이어 입력 및 초기화
        val players = InputView.readPlayerNames()
            .map { Player(it) }

        println()

        // 플레이어의 베팅 금액 입력
        players.forEach { player ->
            val bettingAmount = InputView.readBettingAmount(player, MIN_BETTING_AMOUNT)
            player.placeBet(bettingAmount)
        }

        // 카드 분배 : 플레이어, 딜러
        cardDistributorService.distributeCards(players, dealer)

        // 분배된 카드정보 출력
        ResultView.printDistributedCardInfos(players, dealer)
        println()

        // 플레이어에게 카드 추가 발급 여부 묻기
        players.forEach { player ->
            cardDistributorService.additionalDistributeForPlayer(player)
        }
        println()

        // 딜러에게 카드 추가 발급
        cardDistributorService.additionalDistributeForDealer(dealer)
        println()

        // 딜러 및 플레이어의 최종 카드 출력
        ResultView.printParticipantCardResult(players, dealer)
        println()

        // 최종 승패 출력 -> 스팩아웃 (과거 blackjackResultCalculator.determineWinStatus() 의 결과)
        // 최종 수익 출력
        blackjackResultCalculator.profitReport(players, dealer)
        ResultView.printParticipantProfit(players, dealer)
    }
}