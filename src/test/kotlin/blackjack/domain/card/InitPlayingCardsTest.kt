package blackjack.domain.card

import blackjack.domain.deck.DeckGenerator
import blackjack.domain.model.BlackJackErrorCode
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.throwable.shouldHaveMessage

class InitPlayingCardsTest : StringSpec({

    "시작하는 플레이 카드 리스트를 생성할 때 정해진 개수가 아니라면 생성할 수 없다는 에러가 발생한다." {
        val exception = shouldThrow<IllegalStateException> {
            InitPlayingCards(cards = listOf())
        }

        exception shouldHaveMessage BlackJackErrorCode.MUST_BE_SET_INIT_CARD_COUNT.message(arrayOf(0))
    }

    "시작하는 플레이 카드 리스트를 생성할 때 정해진 개수라면 정상적으로 생성이 된다." {
        val generator = DeckGenerator.generator()

        val cards = List(InitPlayingCards.INIT_CARD_COUNT) {
            generator.removeFirst()
        }

        val playingCards = InitPlayingCards(cards = cards)

        playingCards shouldContainAll cards
    }
})
