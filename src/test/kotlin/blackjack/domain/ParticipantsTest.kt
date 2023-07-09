package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ParticipantsTest : FunSpec({
    context("딜러가 21을 초과하면 딜러는 패배한다.") {
        val dealer = Dealer()
        dealer.addCard(Card(Suit.HEART, Denomination.JACK))
        dealer.addCard(Card(Suit.HEART, Denomination.QUEEN))
        dealer.addCard(Card(Suit.HEART, Denomination.KING))

        val participants = Participants(listOf(dealer, Player("june")))

        participants.isDealerLose() shouldBe true
    }
})
