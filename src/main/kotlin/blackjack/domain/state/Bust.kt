package blackjack.domain.state

import blackjack.domain.Hand

class Bust(hand: Hand) : Finished(hand)
