package blackjack.ui.model

import blackjack.domain.card.Cards

abstract class PlayerViewModel(val name: String, open val cards: Cards)
