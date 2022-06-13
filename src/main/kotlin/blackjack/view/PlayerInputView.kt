package blackjack.view

import blackjack.domain.Bet
import blackjack.domain.dto.GameParticipationRequest

class PlayerInputView(private val io: IO) {

    fun inputPlayerInfo(): List<GameParticipationRequest> {
        io.print("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readPlayerNames()
        io.print("")

        return names.map { readBetAmount(it) }
    }

    private tailrec fun readPlayerNames(): List<String> {
        val names = io.read()
            .split(NAME_DELIMITER)
            .filter { it.isNotBlank() }

        if (names.isEmpty()) {
            io.print("잘못 입력하셨습니다. 다시 입력하세요.")
            return readPlayerNames()
        }

        return names
    }

    private tailrec fun readBetAmount(name: String): GameParticipationRequest {
        runCatching {
            io.print("${name}의 배팅 금액은?")
            val bet = Bet(io.read().toInt())
            io.print("")

            return GameParticipationRequest(name, bet)
        }.getOrElse {
            io.print("잘못 입력하셨습니다. 다시 입력하세요.")
            return readBetAmount(name)
        }
    }

    companion object {
        private const val NAME_DELIMITER = ","
    }
}
