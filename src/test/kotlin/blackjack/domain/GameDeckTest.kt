package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class GameDeckTest : StringSpec({
    "블랙잭 게임 덱은 최초 48장으로 구성된다." {
        val gameDeck = GameDeck()
        val handOutCards = gameDeck.handOutCards(48)

        handOutCards.size shouldBe 48
    }

    "블랙잭 게임 덱의 카드가 존재하지 않을 경우 나눠줄 수 없다." {
        val gameDeck = GameDeck()
        val exception = shouldThrow<IllegalArgumentException> { gameDeck.handOutCards(49) }

        exception.message shouldBe "나눠 줄 카드가 존재하지 않습니다."
    }

    "덱에서 카드를 한 장 나눠줄 수 있다." {
        val gameDeck = GameDeck()
        val card = gameDeck.handOutCard()

        card shouldNotBe null
    }
})
