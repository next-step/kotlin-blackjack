import domain.bet.BetMoney
import domain.card.CardDeck
import domain.participant.Dealer
import domain.participant.Player
import presentation.InputView
import presentation.ResultView
import service.BlackjackWinnerService
import service.CardDistributorService

fun main() {
    // 딜러 초기화
    val dealer = Dealer()

    // 플레이어 이름 입력
    val playerNames = InputView.inputPlayers()
    println()

    // 베팅금액 입력 및 플레이어 초기화
    val players =
        playerNames.map {
            Player(it, BetMoney(InputView.inputBetMoney(it)))
        }
    println()

    // 카드 분배 : 플레이어, 딜러
    val cardDistributorService = CardDistributorService(CardDeck())
    cardDistributorService.distributeInitialCards(players + dealer)

    // 분배된 카드정보 출력
    ResultView.printDistributedCardInfos(players, dealer)
    println()

    // 플레이어에게 카드 추가 발급 여부 묻기
    players.forEach { player ->
        cardDistributorService.distributeAdditionalCardsForPlayer(player)
    }
    println()

    // 딜러에게 카드 추가 발급
    cardDistributorService.distributeAdditionalCardsForDealer(dealer)
    println()

    // 딜러 및 플레이어의 최종 카드 출력
    ResultView.printParticipantCardResult(players, dealer)
    println()

    // 최종 승패 출력
    val blackjackWinnerService = BlackjackWinnerService()
    val result = blackjackWinnerService.determineWinner(players, dealer)
    ResultView.printWinnerResult(result)
}
