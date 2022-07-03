package blackjack.dto

import blackjack.domain.Card
import blackjack.domain.Stat
import blackjack.domain.user.User

open class UserDto(
    val name: String,
    val cards: List<Card>,
    val score: Int
) {

    fun toDealer(statMap: Map<Stat, Int>) = DealerDto(name, cards, score, statMap)

    companion object {
        fun of(user: User) = UserDto(
            name = user.name,
            cards = user.cards(),
            score = user.getScore().value
        )
    }
}
