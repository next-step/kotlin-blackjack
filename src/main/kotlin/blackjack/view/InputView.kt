package blackjack.view

import blackjack.domain.Player

object InputView {
    fun playerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val playerNames =
            readlnOrNull()
                ?.split(",")
                ?.map { it.trim() }

        require(playerNames != null && playerNames.isNotEmpty() && playerNames.all { it.isNotBlank() }) { "올바른 참가자 이름을 입력해주세요." }
        return playerNames
    }

    fun decideDealCard(player: Player): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val decider = readlnOrNull()

        require(decider != null && (decider == "y" || decider == "n")) {
            "올바른 값을 입력해주세요."
        }

        return decider == "y"
    }
}
