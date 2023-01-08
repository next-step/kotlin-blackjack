package blackjack.ui

import blackjack.Tokenizer
import blackjack.domain.Gamer
import blackjack.domain.Player

class InputView {

    fun inputNames(): List<Gamer> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val input = readlnOrNull()
        if (input.isNullOrBlank()) return inputNames()

        return Tokenizer.tokenize(input)
            .map { name ->
                Gamer(name)
            }
    }

    fun ask(): (Player) -> String = { player ->
        println()
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        readlnOrNull() ?: ""
    }
}
