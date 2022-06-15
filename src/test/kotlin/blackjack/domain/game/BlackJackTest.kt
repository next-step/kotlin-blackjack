package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.RandomCardDeck
import blackjack.domain.card.RandomCardDeck.Companion.ACE
import blackjack.domain.card.RandomCardDeck.Companion.DIAMOND
import blackjack.domain.card.RandomCardDeck.Companion.JACK
import blackjack.domain.card.RandomCardDeck.Companion.KING
import blackjack.dto.BlackJackRequest
import blackjack.util.CardDeckFake
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class BlackJackTest : FreeSpec({

    "init" - {
        "블랙잭 게임이 사작할 때 플레이어는 2장의 카드를 받아야한다." {
            val dto = BlackJackRequest.of(listOf("uju"))
            val cards = mutableListOf(
                Card(DIAMOND, "2"),
                Card(DIAMOND, "3"),
                Card(DIAMOND, "4"),
                Card(DIAMOND, "5"),
            )
            val cardDeck = CardDeckFake(cards)

            BlackJack(dto, cardDeck)

            val player = dto.players[0]
            player.cards.size shouldBe 2
            player.cards[0] shouldBe Card(DIAMOND, "2")
            player.cards[1] shouldBe Card(DIAMOND, "3")
        }

        "블랙잭 게임이 시작할 때 딜러는 2장의 카드를 받아야한다." {
            val dto = BlackJackRequest.of(listOf("uju"))
            val cards = mutableListOf(
                Card(DIAMOND, "2"),
                Card(DIAMOND, "3"),
                Card(DIAMOND, "4"),
                Card(DIAMOND, "5"),
            )
            val cardDeck = CardDeckFake(cards)

            BlackJack(dto, cardDeck)

            val dealer = dto.dealer
            dealer.name shouldBe "딜러"
            dealer.cards.size shouldBe 2
            dealer.cards[0] shouldBe Card(DIAMOND, "4")
            dealer.cards[1] shouldBe Card(DIAMOND, "5")
        }
    }

    "giveCard" - {
        "카드 한장을 줘야한다." {
            val dto = BlackJackRequest.of(listOf("uju"))
            val cardDeck = RandomCardDeck()
            val blackJack = BlackJack(dto, cardDeck)
            val player = dto.players[0]

            blackJack.giveCard(player)

            player.cards.size shouldBe 3
        }
    }

    "canHitPlayer" - {
        "플레이어의 카드가 21을 초과하지 않으면 true를 반환한다." {
            val dto = BlackJackRequest.of(listOf("uju"))
            val cards = mutableListOf(
                Card(DIAMOND, "2"),
                Card(DIAMOND, "3"),
                Card(DIAMOND, "4"),
                Card(DIAMOND, "5"),
            )
            val cardDeck = CardDeckFake(cards)
            val blackJack = BlackJack(dto, cardDeck)
            val player = dto.players[0]

            val result = blackJack.canHitPlayer(player)
            result shouldBe true
        }

        "플레이어의 카드가 21을 초과하면 false를 반환한다." {
            val dto = BlackJackRequest.of(listOf("uju"))
            val cards = mutableListOf(
                Card(DIAMOND, ACE),
                Card(DIAMOND, JACK),
                Card(DIAMOND, KING),
                Card(DIAMOND, "5"),
            )
            val cardDeck = CardDeckFake(cards)
            val blackJack = BlackJack(dto, cardDeck)
            val player = dto.players[0]

            val result = blackJack.canHitPlayer(player)
            result shouldBe false
        }
    }
})
