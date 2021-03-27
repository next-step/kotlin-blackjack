package blackjack.domain

import blackjack.ui.model.PlayerDto

interface Participant {
    fun takeCard(card: Card)
    fun cardPointSum(): Int
    fun toPlayerDto(): PlayerDto
}
