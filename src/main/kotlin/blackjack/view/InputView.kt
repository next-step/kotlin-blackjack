package blackjack.view

import blackjack.domain.ParticipantResult

object InputView {

    fun enterNameOfParticipant(): ParticipantResult {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val participant = readlnOrNull() ?: throw IllegalArgumentException("값을 입력하세요.")

        return ParticipantResult(participant)
    }
}
