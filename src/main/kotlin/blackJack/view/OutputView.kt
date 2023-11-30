package blackJack.view

import blackJack.dto.playerDto.DealerDto
import blackJack.dto.playerDto.ParticipantsDto
import blackJack.dto.playerDto.PlayerDto
import blackJack.dto.ResultDto.ResultDto

object OutputView {

    private const val ENTER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val BETTING = " 에게 2장의 나누었습니다."
    private const val QUESTION_YES_OR_NO = "는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)"
    private const val PRINT_ADD_DEALER = "딜러는 16이하라 한장의 카드를 더 받았습니다."

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
        println()
        println(PRINT_ADD_DEALER)
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
        println("## 최종 승패")
        println("딜러: " + resultDto.dealerResult.win + "승 " + resultDto.dealerResult.lose + "패")
        resultDto.playersResult.playerResults.forEach {
            println(it.name + ": " + it.result)
        }
    }
}
