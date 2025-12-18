package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class PlayerTest : FreeSpec({

    "참가자 이름 유효성 체크" - {
        "참가자 이름은 빈 값일 수 없다." {
            val exception = shouldThrow<IllegalArgumentException> { Player("") }

            exception.message shouldBe "참가자 이름은 빈 값일 수 없습니다."
        }
    }

    "참가자 첫번째 라운드 카드 뽑기" {
        val cardDeck = CardDeck()
        val player = Player("player")
        repeat(2) {
            player.cards.addCard(cardDeck.drawCard())
        }

        player.getPublicCardsOnFirstRound().cards.size shouldBe 2
    }
})
