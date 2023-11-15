package blackjack.view

import blackjack.model.Card
import blackjack.model.Participants

object InputView {
    private fun joinPlayers(input: String): Participants {
        return Participants.of(input)
    }

    fun join(): Participants {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val participants: Participants = joinPlayers(readlnOrNull() ?: "")
        participants.dealing()
        println("${participants.names()} 에게 2 장씩 나누었습니다.")
        return participants
    }

    fun draw(participants: Participants) {
        participants.participants.forEach {
            println("${it.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            if (isHit()) {
                it.hit(Card.of())
            }
            println(it.present())
        }
    }

    private fun isHit(): Boolean {
        return (readlnOrNull() ?: "") == "y"
    }
}
