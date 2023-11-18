package blackjack.controller

import blackjack.domain.Player
import blackjack.view.inputNames

fun main() {
    // 참가자 명단 입력
    val players = inputNames().map { Player(it) }
}
