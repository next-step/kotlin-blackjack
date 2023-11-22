package blackjack_dealer.entity

import blackjack_dealer.domain.Dealer

data class BlackJackGamer(
    val dealer: Dealer,
    val participants: Participants,
)
