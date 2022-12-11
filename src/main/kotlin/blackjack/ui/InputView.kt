package blackjack.ui

import blackjack.Tokenizer
import blackjack.domain.Player

class InputView {

    fun inputNames(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val input = readLine()
        if (input.isNullOrBlank()) return inputNames()

        return Tokenizer.tokenize(input)
            .map { name ->
                Player(name)
            }
    }
}
