package blackjack.dto

import blackjack.domain.Card
import blackjack.domain.Stat
import blackjack.domain.user.Dealer

data class DealerDto(
    val name: String,
    val cards: List<Card>,
    val score: Int,
    val statMap: Map<Stat, Int>
) {
    companion object {
        fun of(dealer: Dealer, stats: Map<Stat, Int>): DealerDto {
            return DealerDto(
                name = dealer.name,
                cards = dealer.cards(),
                score = dealer.getScore().value,
                statMap = stats
            )
        }
    }
}
