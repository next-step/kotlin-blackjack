package blackjack.card

import blackjack.domain.BlackJack
import blackjack.entity.participantsFromNames
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackTest : StringSpec({
    val initialSize = 2

    "y를 입력 받으면 이전 보다 카드 개수가 1개 더 많다" {
        // input 이 "y" 면 == 에 의해 true를 반환합니다.
        val inputY = true
        val expectedSize = 3
        val participants = "pita".participantsFromNames()
        val blackJack = BlackJack(participants)

        participants.first().cards.size shouldBe initialSize
        blackJack.doBlackJack(
            printGetOneMoreCard = {},
            input = {
                inputY
            },
            printNewCard = {},
        )
        participants.first().cards.size shouldBe expectedSize
    }

    "n를 입력 받으면 이전과 카드의 개수가 같다 (카드를 받을 수 있는 상황인데도)" {
        // input 이 "n" 면 == 에 의해 false를 반환합니다.
        val inputN = false
        val expectedSize = 2
        val participants = "pita".participantsFromNames()
        val blackJack = BlackJack(participants)

        participants.first().cards.size shouldBe initialSize
        blackJack.doBlackJack(
            printGetOneMoreCard = {},
            input = { inputN },
            printNewCard = {},
        )
        participants.first().cards.size shouldBe expectedSize
    }
})
