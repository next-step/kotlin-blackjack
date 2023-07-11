package blackjack.domain.status

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Deck
import blackjack.domain.user.Dealer
import blackjack.domain.user.Player
import blackjack.domain.user.PlayerGroup
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class HitTest : StringSpec({
    "Hit상태에서 draw를 호출한뒤 BlackJack, Burst 조건이 아닌 경우 Hit을 반환한다 " {
        val player = Player("test")
        val hit = Hit(player)

        val status = hit.draw(Card(CardPattern.Spade, CardNumber.SEVEN))

        status.shouldBeInstanceOf<Hit>()
    }

    "Hit상태에서 draw를 호출한뒤 BlackJack 조건인 경우 BlackJack을 반환한다 " {
        val player = Player("test")
        val hit = Hit(player)

        val status = hit.draw(Card(CardPattern.Spade, CardNumber.A))
            .draw(Card(CardPattern.Spade, CardNumber.TEN))

        status.shouldBeInstanceOf<BlackJack>()
    }

    "Hit상태에서 draw를 호출한뒤 Burst 조건인 경우 Burst를 반환한다 " {
        val player = Player("test")
        val hit = Hit(player)

        val status = hit.draw(Card(CardPattern.Spade, CardNumber.A))
            .draw(Card(CardPattern.Spade, CardNumber.SEVEN))
            .draw(Card(CardPattern.Spade, CardNumber.JACK))

        status.shouldBeInstanceOf<Burst>()
    }

    "Hit상태에서 stay를 하는 경우, Stay를 반환한다" {
        val player = Player("test")
        val hit = Hit(player)

        val status = hit.stay()

        status.shouldBeInstanceOf<Stay>()
    }

})
