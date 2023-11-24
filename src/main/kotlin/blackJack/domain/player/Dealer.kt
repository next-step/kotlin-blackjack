package blackJack.domain.player

import blackJack.domain.card.Cards
import blackJack.domain.enums.Status
import blackJack.domain.enums.Status.*

class Dealer(val name: String, val cards: Cards = Cards(emptyList()), var status: Status = HIT)
