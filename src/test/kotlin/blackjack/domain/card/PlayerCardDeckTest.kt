package blackjack.domain.card

import blackjack.domain.player.PlayerName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerCardDeckTest : StringSpec({

    "카드를 삽입 했다면 비어있지 않다" {
        val playerCardDeck = PlayerCardDeck(PlayerName("test"))
        val card = Card(
            shape = CardShape.CLOVER,
            denomination = CardDenomination.ACE,
        )
        playerCardDeck.insert(card)
        playerCardDeck.isNotEmpty() shouldBe true
    }

    "카드를 삽입하지 않았다면 비어있다" {
        val playerCardDeck = PlayerCardDeck(PlayerName("test"))
        playerCardDeck.isEmpty() shouldBe true
    }

    "다수의 카드를 삽입할 수 있다" {
        val cards = Card.ALL_CARDS
        val playerCardDeck = PlayerCardDeck(PlayerName("test")).apply {
            insertAll(cards)
        }
        playerCardDeck.cards shouldBe cards
    }

    "플레이어 이름과 보유한 카드들을 캡쳐할 수 있다" {
        val playerName = PlayerName("test")
        val cards = Card.ALL_CARDS.take(2)
        val expected = PlayerCardDeckCapture(
            playerName = playerName,
            cards = cards,
        )
        val playerCardDeck = PlayerCardDeck(playerName).apply {
            insertAll(cards)
        }
        playerCardDeck.capture() shouldBe expected
    }
})
