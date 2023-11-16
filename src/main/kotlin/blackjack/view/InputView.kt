package blackjack.view

import blackjack.model.Participants
import blackjack.model.Player
import blackjack.model.pack.ShuffledPack
import blackjack.view.Console.present
import blackjack.view.InputView.PARTICIPANTS_PRESENT_SEPARATOR

object InputView {
    private const val PLAYER_NAMES_DELIMITER: String = ","
    private const val HIT_COMMAND: String = "y"
    const val PARTICIPANTS_PRESENT_SEPARATOR: String = ", "

    private fun joinPlayers(input: String): Participants {
        return Participants(
            input.split(PLAYER_NAMES_DELIMITER)
                .asSequence()
                .map { Player(it) }
                .toSet()
        )
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
                it.hit(ShuffledPack.pickCard())
            }
            println(it.present())
        }
    }

    private fun isHit(): Boolean {
        return (readlnOrNull() ?: "") == HIT_COMMAND
    }
}

private fun Participants.names(): String {
    return participants.joinToString(separator = PARTICIPANTS_PRESENT_SEPARATOR) { it.name }
}
