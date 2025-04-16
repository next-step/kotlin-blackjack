package blackjack.domain.state

import blackjack.domain.card.CardFixture.SPADES_ACE
import blackjack.domain.player.Hands
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec

class BustTest : FunSpec({
    test("addCard throws exception if busted") {
        shouldThrow<IllegalStateException> {
            Bust(Hands()).addCard(SPADES_ACE)
        }
    }
})
