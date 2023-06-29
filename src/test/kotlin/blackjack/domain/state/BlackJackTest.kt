package blackjack.domain.state

import blackjack.domain.card.cards
import blackjack.domain.card.heartAce
import blackjack.domain.card.heartTen
import blackjack.domain.card.heartThree
import blackjack.domain.card.heartTwo
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class BlackJackTest : StringSpec({

    "블랙잭 카드 목록이 아니면 RuntimeException 예외 처리를 한다" {
        val cards = cards(heartTwo(), heartThree())
        shouldThrow<RuntimeException> {
            BlackJack(cards)
        }
    }

    "블랙잭 카드 목록으로 블랙잭 카드 상태를 만들수 있다" {
        val cards = cards(heartAce(), heartTen())
        shouldNotThrow<RuntimeException> {
            BlackJack(cards)
        }
    }
})
