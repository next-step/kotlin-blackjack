import domain.Dealer
import presentation.InputView
import service.CardDistributorService

fun main() {
    // 딜러 초기화
    val dealer = Dealer()

    // 플레이어 입력 및 초기화
    val players = InputView.inputPlayers()
    println()

    // 카드 분배 : 플레이어, 딜러
    val cardDistributorService = CardDistributorService()
    cardDistributorService.distributeCards(players, dealer)

    // 분배된 카드정보 출력
    println("딜러와 ${players.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")
    println("딜러: ${dealer.cards.joinToString(", ")}")
    players.forEach { player ->
        println("${player.name}카드: ${player.cards.joinToString(", ")}")
    }
    println()

    // 플레이어에게 카드 추가 발급 여부 묻기 (Y/N)


}
