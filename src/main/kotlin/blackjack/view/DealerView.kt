package blackjack.view

import blackjack.domain.Card
import blackjack.domain.user.Dealer
import blackjack.dto.DealerDto
import blackjack.dto.UserDto

object DealerView {

    private val cardsTemplate = { dealer: DealerDto -> "${dealer.name} 카드 : ${dealer.cards.joinToString()}" }

    fun printCard(dealer: UserDto, card: Card) {
        println("${dealer.name}: $card")
    }

    fun printMoreCard(user: UserDto) {
        println("${user.name}는 ${Dealer.STOP_POINT}점 미만이라 한 장의 카드를 더 받았습니다.")
    }

    fun printCardsWithResult(dealer: DealerDto) {
        println("${cardsTemplate(dealer)} - 결과: ${dealer.score}")
    }
}
