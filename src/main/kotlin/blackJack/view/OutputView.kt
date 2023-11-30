package blackJack.view

import blackJack.dto.ResultDto.ResultDto
import blackJack.dto.playerDto.ParticipantsDto
import blackJack.dto.playerDto.PlayerDto

object OutputView {

    private const val ENTER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val BETTING = " 에게 2장의 나누었습니다."
    private const val QUESTION_YES_OR_NO = "는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)"
    private const val PRINT_ADD_DEALER = "딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val PRINT_BETTING_PRICE = "의 배팅 금액은?"

    fun printEnterName() = println(ENTER_NAME)
    fun printPlayer(splitNames: List<String>) {
        val names = splitNames.joinToString(", ")
        println("딜러와 $names $BETTING")
    }

    fun printPlayerCards(participants: ParticipantsDto) {
        val dealerCardInfo = participants.dealer.cardsDto.cardDtos.joinToString(", ") { "${it.rank} ${it.suit}" }
        println("딜러 : $dealerCardInfo")

        participants.players.forEach { players ->
            val playersCardsInfo = players.cardsDto.cardDtos.joinToString(", ") { "${it.rank} ${it.suit}" }
            println("${players.name}카드: $playersCardsInfo")
        }
        println()
    }

    fun printDealerCard(player: PlayerDto) {
        val cardsInfo = player.cardsDto.cardDtos.joinToString(", ") { "${it.rank} ${it.suit}" }
        println("${player.name}카드: $cardsInfo\n")
    }

    fun printAddDealer() {
        println(PRINT_ADD_DEALER)
        println()
    }

    fun printQuestionYesOrNo(playerDto: PlayerDto) {
        println(playerDto.name + QUESTION_YES_OR_NO)
    }

    fun printResult(participants: ParticipantsDto) {
        val dealerCardInfo = participants.dealer.cardsDto.cardDtos.joinToString(", ") { "${it.rank} ${it.suit}" }
        println("딜러 카드: $dealerCardInfo - 결과: ${participants.dealer.totalScore}")

        participants.players.forEach { player ->
            val cardsInfo = player.cardsDto.cardDtos.joinToString(", ") { "${it.rank} ${it.suit}" }
            println("${player.name}카드: $cardsInfo - 결과: ${player.totalScore}")
        }
        println()
    }

    fun printWinner(resultDto: ResultDto) {
        println("## 최종 수익")
        val dealerProfit = -resultDto.playersResult.playerResults.sumOf { (it.bettingPrice * it.result).toInt() }
        println("딜러: $dealerProfit")
        resultDto.playersResult.playerResults.forEach {
            val profit = (it.bettingPrice * it.result).toInt()
            println(it.name + ": " + profit)
        }
    }

    fun printBettingPrice(playerName: String) = println(playerName + PRINT_BETTING_PRICE)
}
