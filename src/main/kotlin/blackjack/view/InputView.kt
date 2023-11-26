package blackjack.view

import blackjack.model.Participants
import blackjack.model.Players
import blackjack.model.playable.impl.Dealer
import blackjack.model.playable.impl.Player

object InputView {
    private const val PLAYER_NAMES_DELIMITER: String = ","

    const val PARTICIPANTS_PRESENT_SEPARATOR: String = ", "

    private fun joinPlayers(input: String): Participants {
        return Participants(
            Players(
                input.split(PLAYER_NAMES_DELIMITER)
                    .asSequence()
                    .map { Player(it) }
                    .toSet()
            ),
            Dealer()
        )
    }

    fun join(): Participants {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return joinPlayers(readlnOrNull() ?: "")
    }
}
