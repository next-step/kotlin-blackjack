package blackjack.dto

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Stat

data class DealerDto(
    val name: String,
    val cards: List<Card>,
    val point: Int,
    val statMap: Map<Stat, Int>
) {
    companion object {
        fun of(dealer: Dealer, stats: Map<Stat, Int>): DealerDto {
            return DealerDto(
                name = dealer.name,
                cards = dealer.cards.toList(),
                point = dealer.getPoints(),
                statMap = stats
            )
        }
    }
}
