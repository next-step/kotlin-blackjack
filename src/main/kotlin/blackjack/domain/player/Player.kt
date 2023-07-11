package blackjack.domain.player

import blackjack.domain.Money
import blackjack.domain.Playable
import blackjack.domain.card.Hand

class Player(val name: PlayerName, val money: Money) : Playable(Hand.empty())
