package blackjack.domain.cards

import blackjack.domain.card.Card
import blackjack.domain.card.Character
import blackjack.domain.card.Suit
import io.kotest.core.spec.style.StringSpec
import org.assertj.core.api.Assertions
import java.lang.IllegalArgumentException

class HandCardsTest : StringSpec({
    "Hand 는 초기에 2 장의 카드로 이루어져야 한다" {
        Assertions.assertThatThrownBy {
            HandCards(
                mutableListOf(
                    Card(Suit.Spade, Character.Jack),
                    Card(Suit.Clover, Character.Ace),
                    Card(Suit.Clover, Character.Ace),
                )
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Hand should be initialized with 2 cards")
    }
})
