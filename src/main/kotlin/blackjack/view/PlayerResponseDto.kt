package blackjack.view

import blackjack.domain.deck.Card
import blackjack.domain.player.Player

class PlayerResponseDto(player: Player) {
    val name = player.name
    val cards = player.status.cards.cards.map { CardResponseDto(it) }
    val score = player.score()

    class CardResponseDto(card: Card) {
        val denomination = card.denomination.denomination
        val suitName = card.suit.koreanName
    }
}
