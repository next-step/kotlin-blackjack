package camp.nextstep.blackjack.ui

import camp.nextstep.blackjack.player.Player

class PlayerHand(player: Player) {
    val playerName = player.name
    val faceUpCards = player.hand.faceUpCards
    val faceDownCardCount = player.hand.faceDownCardCount

    override fun toString(): String {
        return "${playerName}의 카드: ${faceUpCards.joinToString { "{${it.number}:${it.suit}}" }} ${"{?:?}".repeat(faceDownCardCount)}"
    }
}
