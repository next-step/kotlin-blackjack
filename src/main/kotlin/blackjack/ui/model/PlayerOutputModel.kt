package blackjack.ui.model

import blackjack.domain.card.Cards

abstract class PlayerOutputModel(val name: String, open val cards: Cards)
