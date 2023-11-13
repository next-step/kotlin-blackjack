package blackjack.ui

import blackjack.model.Participant

object InputView {

    fun registerParticipants(): List<Participant> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readlnOrNull()
            ?.split(',')
            ?.map { name ->
                Participant(name)
            }
            ?.toList()
            ?: throw IllegalArgumentException("참여자의 이름은 null을 허용하지 않습니다.")
    }

    fun askCardPicking(name: String): Boolean {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readlnOrNull()?.let {
            when (it) {
                "y" -> true
                "n" -> false
                else -> throw IllegalArgumentException("카드 받기 여부는 y 또는 n만 입력 가능합니다.")
            }
        } ?: throw IllegalArgumentException("카드 받기 여부는 null을 허용하지 않습니다.")
    }
}
