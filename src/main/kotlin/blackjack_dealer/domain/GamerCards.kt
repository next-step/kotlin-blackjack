package blackjack_dealer.domain

import blackjack_dealer.entity.Card

data class GamerCards(
    val trumpCard: List<Card>
): List<Card> by trumpCard
