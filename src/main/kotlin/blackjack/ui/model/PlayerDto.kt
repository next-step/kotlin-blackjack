package blackjack.ui.model

import blackjack.domain.Card

class PlayerDto(
    val name: String,
    val cards: Set<Card>
)
