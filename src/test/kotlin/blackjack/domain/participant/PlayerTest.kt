package blackjack.domain.participant

import blackjack.domain.deck.Card
import blackjack.domain.deck.CardNumber.ACE
import blackjack.domain.deck.CardNumber.KING
import blackjack.domain.deck.CardNumber.TEN
import blackjack.domain.deck.CardPattern.CLOVER
import blackjack.domain.deck.CardPattern.DIAMOND
import blackjack.domain.deck.CardPattern.HEART
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly

internal class PlayerTest : FreeSpec({

    "카드를 받으면 손패에 카드가 추가된다." - {
        listOf(
            Card(pattern = CLOVER, number = ACE),
            Card(pattern = HEART, number = KING),
            Card(pattern = DIAMOND, number = TEN),
        ).forEach { card ->
            "$card 를 받으면 손패에 $card 가 생긴다." {
                val 규남님 = Player.from("규남님")
                규남님.receiveCard(card = card)

                규남님.hand.shouldContainExactly(card)
            }
        }
    }
})
