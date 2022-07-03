package blackjack.dto

import blackjack.domain.Card
import blackjack.domain.Stat
import blackjack.domain.user.Dealer

class DealerDto(
    name: String,
    cards: List<Card>,
    score: Int,
    val statMap: Map<Stat, Int>
) : UserDto(name, cards, score) {

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
