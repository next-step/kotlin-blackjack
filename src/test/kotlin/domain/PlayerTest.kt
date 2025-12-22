package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class PlayerTest : FreeSpec({

    "플레이어 이름 유효성 체크" - {
        "플레이어 이름은 빈 값일 수 없다." {
            val exception = shouldThrow<IllegalArgumentException> { Player("", 1000) }

            exception.message shouldBe "플레이어 이름은 빈 값일 수 없습니다."
        }
    }

    "플레이어 베팅 금액 유효성 체크" - {
        "플레이어 이름은 빈 값일 수 없다." {
            val exception = shouldThrow<IllegalArgumentException> { Player("player", 0) }

            exception.message shouldBe "베팅 금액은 0 보다 커야합니다."
        }
    }

    "플레이어 첫번째 라운드 카드 뽑기" {
        val cardDeck = CardDeck()
        val player = Player("player", 1000)
        repeat(2) {
            player.cards.addCard(cardDeck.drawCard())
        }

        player.getPublicCardsOnFirstRound().cards().size shouldBe 2
    }

    "플레이어 새로운 카드 뽑을 수 있는지 확인" - {
        "플레이어가 가진 카드가 21일 때 가능" {
            val player = Player("player", 1000)
            player.cards.addCard(Card(Suit.HEART, Rank.JACK))
            player.cards.addCard(Card(Suit.HEART, Rank.QUEEN))

            player.isDrawAvailable() shouldBe true
        }
        "플레이어가 가진 카드가 22일 때 불가" {
            val player = Player("player", 1000)
            player.cards.addCard(Card(Suit.HEART, Rank.JACK))
            player.cards.addCard(Card(Suit.HEART, Rank.QUEEN))
            player.cards.addCard(Card(Suit.HEART, Rank.ACE))

            player.isDrawAvailable() shouldBe false
        }
    }
})
