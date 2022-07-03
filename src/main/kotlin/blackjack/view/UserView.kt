package blackjack.view

import blackjack.dto.UserDto

object UserView {
    private val cardsTemplate = { user: UserDto -> "${user.name} 카드 : ${user.cards.joinToString()}" }

    fun printCards(user: UserDto) {
        println(cardsTemplate(user))
    }
}
