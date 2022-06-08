package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardDeckImpl
import blackjack.domain.game.BlackJack
import blackjack.dto.BlackJackRequest
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class BlackJackTest : FreeSpec({

    "init" - {
        "블랙잭 게임이 사작할 때 플레이어는 2장의 카드를 받아야한다." {
            val dto = BlackJackRequest.of(listOf("uju"))
            val cards = mutableListOf(Card("다이아몬드", "2"), Card("다이아몬드", "3"))
            val cardDeck = CardDeck.Fake(cards)

            BlackJack(dto, cardDeck)

            val player = dto.players[0]
            player.cards.size shouldBe 2
            player.cards[0] shouldBe Card("다이아몬드", "2")
            player.cards[1] shouldBe Card("다이아몬드", "3")
        }
    }

    "giveCard" - {
        "카드 한장을 줘야한다." {
            val dto = BlackJackRequest.of(listOf("uju"))
            val cardDeck = CardDeckImpl()
            val blackJack = BlackJack(dto, cardDeck)
            val player = dto.players[0]

            blackJack.giveCard(player)

            player.cards.size shouldBe 3
        }
    }
})
