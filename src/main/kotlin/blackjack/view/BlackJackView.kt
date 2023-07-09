package blackjack.view

import blackjack.domain.player.Player

object BlackJackView {
    fun printPlayerInputView() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    fun printPlayerCardsView(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString { ", " }}")
    }

    fun printPlayerMoreCardView(player: Player) {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n")
    }
}
