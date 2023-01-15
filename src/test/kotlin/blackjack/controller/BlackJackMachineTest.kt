package blackjack.controller

import blackjack.domain.BlackJackMachine
import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.CardSuit
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class BlackJackMachineTest : StringSpec({
    "게임을 시작하면 참여자들은 카드 2장을 받는다." {
        val player = Player(name = "lisa")
        val cardDeck = CardDeck()
        val blackJackMachine = BlackJackMachine(cardDeck = cardDeck, participants = listOf(player))

        blackJackMachine.initialize()

        player.cards.size shouldBe 2
        cardDeck.cards.size shouldBe 50
    }

    "카드 덱에서 retryFunc 횟수만큼 카드를 가져온다." {
        val player = Player(name = "lisa")
        val cards = Cards(
            mutableListOf(
                Card(CardSuit.CLOVER, Denomination.TWO),
                Card(CardSuit.HEART, Denomination.TWO),
                Card(CardSuit.DIAMOND, Denomination.TWO),
                Card(CardSuit.SPADE, Denomination.TWO),
            )
        )
        val cardDeck = CardDeck(cards)
        val blackJackMachine = BlackJackMachine(cardDeck = cardDeck, participants = listOf(player))

        blackJackMachine.execute(
            retryFunc = { player -> player.cards.size < 4 },
            playerCardResultFunc = { player -> println(player) },
            dealerCardResultFunc = { dealer -> println(dealer) }
        )

        player.cards.size shouldBe 4
    }
})
