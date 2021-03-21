package blackjack.ui.model

import blackjack.domain.Card

class PlayerDTO(
    val name: String,
    val cards: Set<Card>
)
