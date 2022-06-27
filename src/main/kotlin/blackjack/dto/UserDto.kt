package blackjack.dto

import blackjack.domain.Card
import blackjack.domain.user.User

data class UserDto(
    val name: String,
    val cards: List<Card>,
    val score: Int
) {
    companion object {
        fun of(user: User) = UserDto(
            name = user.name,
            cards = user.cards(),
            score = user.getScore().value
        )
    }
}
