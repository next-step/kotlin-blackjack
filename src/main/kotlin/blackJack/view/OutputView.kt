package blackJack.view

import blackJack.domain.Players
import blackJack.dto.PlayersDto

object OutputView {

    private const val ENTER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val BETTING = " 에게 2장의 나누었습니다."

    fun printEnterName() = println(ENTER_NAME)
    fun printPlayer(splitNames: List<String>) {
        val names = splitNames.joinToString(", ")
        println(names + BETTING)
    }

    fun printPlayerCards(players: PlayersDto) {
        players.playerDtos.forEach { player ->
            val cardsInfo = player.cardsDto.cardDtos.joinToString(", ") { "${it.rank} ${it.suit}" }
            println("${player.name}카드: $cardsInfo")
        }
    }
}
