package blackjack.card

import blackjack.BlackJack
import blackjack.domain.Card
import blackjack.entity.CardNumber
import blackjack.entity.CardShape
import blackjack.entity.Cards
import blackjack.entity.participantsFromNames
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackTest : StringSpec({
    val initialSize = 2
    val cardDeque = ArrayDeque<Card>(
        listOf(
            Card(CardNumber.J, CardShape.CLOVER),
            Card(CardNumber.TWO, CardShape.HEART),
            Card(CardNumber.THREE, CardShape.DIAMOND)
        )
    )
    val deque = Cards(cardDeque)
    val participants = "pita".participantsFromNames(deque)
    val blackJack = BlackJack(deque)

    "y를 입력 받으면 이전 보다 카드 개수가 1개 더 많다" {
        // input 이 "y" 면 == 에 의해 true를 반환합니다.
        val inputY = true
        val expectedSize = 3

        participants.first().cards.size shouldBe initialSize
        val result = participants.map { participant ->
            blackJack.doBlackJack(
                participant = participant,
                printGetOneMoreCard = { "y" },
                input = { inputY },
                printNewCard = {},
            )
        }
        result.first().cards.size shouldBe expectedSize
    }

    "n를 입력 받으면 이전과 카드의 개수가 같다 (카드를 받을 수 있는 상황인데도)" {
        // input 이 "n" 면 == 에 의해 false를 반환합니다.
        val inputN = false
        val expectedSize = 2

        participants.first().cards.size shouldBe initialSize
        val result = participants.map { participant ->
            blackJack.doBlackJack(
                participant = participant,
                printGetOneMoreCard = {},
                input = { inputN },
                printNewCard = {},
            )
        }
        result.first().cards.size shouldBe expectedSize
    }
})
