package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.view.dto.CardDto
import blackjack.view.dto.PlayerDto

class Player(
    val name: String,
    val cards: Cards = Cards(),
) {
    fun receiveCard(card: Card): Boolean {
        if (!canReceive(card)) {
            return false
        }
        cards.add(card)

        return true
    }

    private fun canReceive(card: Card): Boolean {
        if (cards.getScore().isBlackJack()) {
            return false
        }

        if (cards.getScore().isGreaterThanMaxScore(card.number.value)) {
            return false
        }

        return true
    }

    companion object {
        fun toDto(player: Player): PlayerDto =
            PlayerDto(
                name = player.name,
                cards = player.cards.getCards().map { card -> CardDto(card.shape.symbol, card.number.value) },
            )
    }
}
