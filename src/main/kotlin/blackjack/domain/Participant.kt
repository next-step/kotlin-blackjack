package blackjack.domain

import blackjack.ui.model.PlayerDto

interface Participant {
    fun takeCard(card: Card): Boolean
    fun calculateCardSum(): Int
    fun toPlayerDto(): PlayerDto
}
