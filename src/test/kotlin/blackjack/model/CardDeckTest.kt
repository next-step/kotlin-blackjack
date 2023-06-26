package blackjack.model

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.types.shouldBeInstanceOf

@DisplayName("카드 덱")
class CardDeckTest : StringSpec({

    "비어있지 않은 카드들로 덱 생성" {
        shouldNotThrowAny {
            CardDeck()
        }
    }

    "카드를 뽑을 수 있음" {
        // given
        val cardDeck = CardDeck()
        // when
        assertSoftly {
            cardDeck.draw()
                .shouldNotBeNull()
                .shouldBeInstanceOf<TrumpCard>()
            cardDeck.cards shouldHaveSize 51
        }
    }
})
