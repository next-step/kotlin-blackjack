package blackjack.controller

import blackjack.domain.BlackJackMachine
import blackjack.domain.CardDeck
import blackjack.domain.Participant
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class BlackJackMachineTest : StringSpec({
    "게임을 시작하면 참여자들은 카드 2장을 받는다." {
        val player = Participant(name = "lisa")
        val cardDeck = CardDeck()
        val blackJackMachine = BlackJackMachine(cardDeck = cardDeck, players = listOf(player))

        blackJackMachine.initialize()

        player.cards.size shouldBe 2
        cardDeck.cards.size shouldBe 50
    }
})
