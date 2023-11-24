package blackJack.view

import blackJack.dto.DealerDto
import blackJack.dto.ParticipantsDto
import blackJack.dto.PlayerDto
import blackJack.dto.PlayersDto

object OutputView {

    private const val ENTER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val BETTING = " 에게 2장의 나누었습니다."
    private const val QUESTION_YES_OR_NO = "는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)"

    fun printEnterName() = println(ENTER_NAME)
    fun printPlayer(splitNames: List<String>) {
        val names = splitNames.joinToString(", ")
        println(names + BETTING)
    }

    fun printPlayerCards(participants: ParticipantsDto) {
        val dealerCardInfo = participants.dealer.cardsDto.cardDtos.joinToString(", ") { "${it.rank} ${it.suit}" }
        println("딜러 카드: $dealerCardInfo")

        participants.players.forEach { players ->
            val playersCardsInfo = players.cardsDto.cardDtos.joinToString(", ") { "${it.rank} ${it.suit}" }
            println("${players.name}카드: $playersCardsInfo")
        }
        println()
    }

    fun printPlayerCard(player: PlayerDto) {
        val cardsInfo = player.cardsDto.cardDtos.joinToString(", ") { "${it.rank} ${it.suit}" }
        println("${player.name}카드: $cardsInfo\n")
    }

    fun printPlayerCard(dealer: DealerDto) {
        val cardsInfo = dealer.cardsDto.cardDtos.joinToString(", ") { "${it.rank} ${it.suit}" }
        println("${dealer.name}카드: $cardsInfo\n")
    }

    fun printQuestionYesOrNo(playerDto: PlayerDto) {
        println(playerDto.name + QUESTION_YES_OR_NO)
    }

    fun printResult(participants: ParticipantsDto) {
        val dealerCardInfo = participants.dealer.cardsDto.cardDtos.joinToString(", ") { "${it.rank} ${it.suit}" }
        println("딜러 카드: $dealerCardInfo - 결과: ${dealerCardInfo.totalScore}")

        participants.players.forEach { player ->
            val cardsInfo = player.cardsDto.cardDtos.joinToString(", ") { "${it.rank} ${it.suit}" }
            println("${player.name}카드: $cardsInfo - 결과: ${player.totalScore}")
        }
        println()
    }
}
