package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Cards
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.types.shouldBeInstanceOf

class HitTest : StringSpec({
    "카드를 추가로 받았는데 점수가 21점이 넘으면 버스트가 된다." {
        val hit = Hit(
            cards = Cards().apply {
                add(Card(number = CardNumber.TEN, pattern = CardPattern.CLOVER))
                add(Card(number = CardNumber.TEN, pattern = CardPattern.HEART))
            }
        )

        val card = Card(number = CardNumber.TWO, pattern = CardPattern.HEART)
        val newState = hit.receiveCard(card)

        newState.shouldBeInstanceOf<Bust>()
    }

    "카드를 추가로 받았는데 점수가 21점이 넘지 않으면 현재 상태를 유지한다." {
        val hit = Hit(cards = Cards())

        val card = Card(number = CardNumber.TWO, pattern = CardPattern.HEART)
        val newState = hit.receiveCard(card)

        newState.shouldBeInstanceOf<Hit>()
    }
})
