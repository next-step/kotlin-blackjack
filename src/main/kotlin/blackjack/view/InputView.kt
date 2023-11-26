package blackjack.view

import blackjack.model.Participants
import blackjack.model.Players
import blackjack.model.playable.impl.Dealer
import blackjack.model.playable.impl.Player
import blackjack.model.playblestrategy.PlayingStrategy
import blackjack.model.playblestrategy.impl.HitStrategy
import blackjack.model.playblestrategy.impl.StandStrategy

object InputView {
    private const val PLAYER_NAMES_DELIMITER: String = ","
    private const val HIT_COMMAND: String = "y"
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

    private fun isHitInput(): Boolean {
        return (readlnOrNull() ?: "") == HIT_COMMAND
    }

    fun askHit(): PlayingStrategy {
        println("플레이어는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        if (this.isHitInput()) {
            return HitStrategy
        }
        return StandStrategy
    }
    // TODO
//    fun askHit(it: Player): PlayingStrategy {
//        println("${it.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
//        if (this.isHitInput()) {
//            return HitStrategy
//        }
//        return StandStrategy
//    }
}
