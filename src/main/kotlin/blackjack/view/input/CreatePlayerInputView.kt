package blackjack.view.input

import blackjack.view.dto.CreatePlayersDto

object CreatePlayerInputView {
    fun parse(): List<CreatePlayersDto> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        val names = readln().split(",").map { it.trim() }

        return names.map { name ->
            println("${name}의 베팅 금액은?")
            val betting = readln()
            CreatePlayersDto(name = name, betting = betting.toInt())
        }
    }
}
