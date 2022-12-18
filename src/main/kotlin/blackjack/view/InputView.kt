package blackjack.view

import blackjack.domain.ParticipantResult
import blackjack.dto.StayResult

object InputView {

    fun enterNameOfParticipant(): ParticipantResult {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val participant = readlnOrNull() ?: throw IllegalArgumentException("값을 입력하세요.")

        return ParticipantResult(participant)
    }

    fun stay(name: String): StayResult {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val answer = readln()
        return runCatching { StayResult(answer) }
            .fold(
                onSuccess = { it },
                onFailure = { stay(name) }
            )
    }
}
