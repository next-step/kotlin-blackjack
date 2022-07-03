package blackjack.domain.participant

import blackjack.domain.deck.Card
import blackjack.domain.deck.CardNumber.ACE
import blackjack.domain.deck.CardNumber.KING
import blackjack.domain.deck.CardNumber.NINE
import blackjack.domain.deck.CardNumber.QUEEN
import blackjack.domain.deck.CardNumber.SEVEN
import blackjack.domain.deck.CardNumber.TEN
import blackjack.domain.deck.CardPattern.CLOVER
import blackjack.domain.deck.CardPattern.DIAMOND
import blackjack.domain.deck.CardPattern.HEART
import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactly

internal class PlayerTest : FreeSpec({

    "초기 카드를 받으면 손패에 2장의 카드가 추가된다." - {
        listOf(
            row(Card(pattern = CLOVER, number = ACE), Card(pattern = CLOVER, number = QUEEN)),
            row(Card(pattern = HEART, number = KING), Card(pattern = CLOVER, number = NINE)),
            row(Card(pattern = DIAMOND, number = TEN), Card(pattern = CLOVER, number = SEVEN)),
        ).forEach { (firstCard, secondCard) ->
            "$firstCard, $secondCard 를 받으면 손패에 $firstCard, $secondCard 가 생긴다." {
                val 규남님 = Player.of(nameValue = "규남님", firstCard, secondCard)
                규남님.cards().shouldContainExactly(firstCard, secondCard)
            }
        }
    }
})
