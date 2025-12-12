import domain.CardDeck
import domain.Dealer
import presentation.InputView
import presentation.ResultView
import service.BlackjackWinnerService
import service.CardDistributorService

fun main() {
    // 딜러 초기화
    val dealer = Dealer()

    // 플레이어 입력 및 초기화
    val players = InputView.inputPlayers()
    println()

    // 카드 분배 : 플레이어, 딜러
    val cardDistributorService = CardDistributorService(CardDeck())
    cardDistributorService.distributeCards(players, dealer)

    // 분배된 카드정보 출력
    println("딜러와 ${players.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")
    println("딜러: ${dealer.blackjackCards.printCards()}")
    players.forEach {
        println("${it.name}카드: ${it.blackjackCards.printCards()}")
    }
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

    // 최종 승패 출력
    val blackjackWinnerService = BlackjackWinnerService()
    val result = blackjackWinnerService.winner(players, dealer)
    ResultView.printWinnerResult(result)
}
