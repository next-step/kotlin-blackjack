package blackjack.view.input

import blackjack.view.dto.CreatePlayersDto

object CreatePlayerInputView {
    fun print(): CreatePlayersDto {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return CreatePlayersDto(readln().split(",").map { it.trim() })
    }
}
