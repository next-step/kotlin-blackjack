package blackjack.view

import blackjack.domain.player.Participant
import blackjack.domain.player.vo.Name

private const val NAME_SPLITTER = ","

class ConsoleInput : Input {
    override fun askParticipantNames(): List<Name> {
        return try {
            println()
            println("게임에 참여할 사람의 이름을 입력하세요.(${NAME_SPLITTER}를 기준으로 분리)")
            readln().split(NAME_SPLITTER).map { Name(it.trim()) }
        } catch (e: Exception) {
            println("잘못된 입력입니다.")
            askParticipantNames()
        }
    }

    override fun askDrawMoreCard(participant: Participant): String {
        return try {
            println()
            println("${participant.name}은(는) 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            readln()
        } catch (e: Exception) {
            println("잘못된 입력입니다.")
            askDrawMoreCard(participant)
        }
    }
}
