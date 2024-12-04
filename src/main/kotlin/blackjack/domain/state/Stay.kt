package blackjack.domain.state

import blackjack.domain.Hand

class Stay(hand: Hand) : Finished(hand)
