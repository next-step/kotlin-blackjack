package blackjack.domain

import blackjack.ui.model.PlayerCardResult
import blackjack.ui.model.PlayerDto
import blackjack.ui.model.PlayerWinTypes

class Dealer(
    cards: Set<Card> = emptySet()
) : Participant {

    private val player = Player("딜러", cards)
    val cardResult: PlayerCardResult
        get() = PlayerCardResult(player)
    val isUnderSixteen: Boolean
        get() = this.cardPointSum() <= DEALER_POINT_TO_TAKE_MORE_CARD
    val cardSize
        get() = player.cardSize

    override fun takeCard(card: Card) {
        player.takeCard(card)
    }

    override fun cardPointSum(): Int {
        return player.cardPointSum()
    }

    override fun toPlayerDto(): PlayerDto {
        return player.toPlayerDto()
    }

    companion object {
        private const val DEALER_POINT_TO_TAKE_MORE_CARD = 16
    }
}
