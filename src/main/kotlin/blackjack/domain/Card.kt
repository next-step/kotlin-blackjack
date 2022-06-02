package blackjack.domain

import blackjack.enums.Type
import blackjack.enums.Value

data class Card(val type: Type, val value: Value)
