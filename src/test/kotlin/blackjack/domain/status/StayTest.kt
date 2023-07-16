package blackjack.domain.status

import blackjack.domain.card.*
import blackjack.domain.user.Dealer
import blackjack.domain.user.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.types.shouldBeInstanceOf

class StayTest : StringSpec({

    "Stay상태에서 player가 dealer보다 BLACK_JACK_SCORE보다 차이가 큰 경우, judge를 호출시 Lose가 반환된다" {
        val player = Player("test")
        player.draw(Card(CardPattern.Spade, CardNumber.SEVEN))
        player.stay()

        val dealer = Dealer()
        dealer.draw(Card(CardPattern.Spade, CardNumber.TEN))

        player.calculateResult(dealer)

        player.status.shouldBeInstanceOf<Lose>()
    }

    "Stay상태에서 player가 dealer보다 BLACK_JACK_SCORE보다 차이가 작은 경우, judge를 호출시 Win이 반환된다" {
        val player = Player("test")
        player.draw(Card(CardPattern.Spade, CardNumber.SEVEN))
        player.stay()

        val dealer = Dealer()
        dealer.draw(Card(CardPattern.Spade, CardNumber.SIX))

        player.calculateResult(dealer)

        player.status.shouldBeInstanceOf<Win>()
    }
})
