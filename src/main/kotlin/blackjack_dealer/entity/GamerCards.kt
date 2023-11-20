package blackjack_dealer.entity

data class GamerCards(
    val trumpCard: List<Card>
): List<Card> by trumpCard
