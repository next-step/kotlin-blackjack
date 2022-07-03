package blackjack.domain.participant.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.CardNumber
import blackjack.domain.deck.CardPattern
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.types.shouldBeInstanceOf

internal class InitTest : FreeSpec({

    "초기 상태에서 발급 받은 두 장의 카드 점수 합계가" - {
        "21일 경우 블랙잭 상태를 반환한다." {
            val firstCard = Card(pattern = CardPattern.CLOVER, number = CardNumber.TEN)
            val secondCard = Card(pattern = CardPattern.CLOVER, number = CardNumber.ACE)

            Init.receiveCard(firstCard = firstCard, secondCard = secondCard).shouldBeInstanceOf<Blackjack>()
        }

        "21 미만일 경우 힛 상태를 반환한다." {
            val firstCard = Card(pattern = CardPattern.CLOVER, number = CardNumber.TEN)
            val secondCard = Card(pattern = CardPattern.CLOVER, number = CardNumber.QUEEN)
            Init.receiveCard(firstCard = firstCard, secondCard = secondCard).shouldBeInstanceOf<Hit>()
        }
    }
})
