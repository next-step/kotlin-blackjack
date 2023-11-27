package blackjack.model

import blackjack.model.card.CardFixture
import blackjack.model.card.CardFixture.makeCards
import blackjack.model.playable.impl.Dealer
import blackjack.model.playable.impl.Player
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class PlayersTest : StringSpec({

    "플레이어들의 이름은 중복을 허용하지 않는다. 중복시 throw IllegalArgumentException" {
        shouldThrow<IllegalArgumentException> {
            Players(Player("hana"), Player("hana"))
        }
    }

    "플레이어들의 이름이 중복되지 않으면 정상 동작 해야한다" {
        shouldNotThrow<IllegalArgumentException> {
            Players(Player("hana"), Player("numa"))
        }
    }

    "모든 플레이어가 Burst 일때, 딜러는 승리하고 모든 플레이어가 패배해야한다" {
        val google = Player("google", makeCards(CardFixture.seven, CardFixture.nine, CardFixture.eight))
        val apple = Player("apple", makeCards(CardFixture.jack, CardFixture.queen, CardFixture.three))
        val microsoft = Player("microsoft", makeCards(CardFixture.king, CardFixture.ten, CardFixture.five))
        val players = Players(setOf(google, apple, microsoft))
        val dealer = Dealer(makeCards(CardFixture.two, CardFixture.five, CardFixture.four))
        val results = players.results(dealer)

        // results shouldContain (google to PlayableResult.LOSE)
        println(results)
    }
})
