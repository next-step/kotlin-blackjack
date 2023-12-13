package game.blackjack

import game.blackjack.domain.Deck
import game.blackjack.domain.Participant

fun main() {
    // 게임에 참여할 사람을 입력받는다.
    val participants = readln().split(",").map { Participant(it) }

    // 총 52장의 카드로 이루어진 게임 덱을 받는다.
    val deck = Deck()

    // 게임 시작 시 각 참여자들은 2장의 카드를 받는다.
    participants.forEach { it.drawCard(deck.initialDraw()) }

    // 카드를 받을 경우 각 참여자들이 가진 패를 출력한다.
    println(String.format("%s에게 2장을 나누었습니다.", participants.map { it.name }.joinToString(", ")))
    participants.forEach { println(it) }

    // 카드의 총 숫자가 21 이하면 계속 카드를 뽑을 수 있다.
    participants.forEach { participant ->
        while (participant.isNotBust()) {
            println(String.format("${participant.name}은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"))
            if (readln().trim().lowercase() != "y") {
                break
            }
            participant.drawCard(deck.drawOnce())
            println(participant)
        }
    }

    participants.forEach {
        print(it)
        println(" - 결과: ${it.getScore()}")
    }
}
