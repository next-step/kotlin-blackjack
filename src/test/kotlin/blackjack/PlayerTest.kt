package blackjack

import blackjack.domain.BlackjackRule
import blackjack.domain.Dealer
import blackjack.domain.Player
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

data class PlayerInitialCardTestData(
    val name: String,
    val dealer: Dealer,
)

class PlayerTest : FunSpec({
    context("Player는 이름을 가진다") {
        withData(
            listOf(
                "김영태",
                "기임영태"
            )
        ) { name ->
            val player = Player(name, Dealer.withFullDeck())

            player.name shouldBe name
        }
    }

    context("Player는 처음에 2장의 카드를 Dealer로부터 받는다.") {
        withData(
            listOf(
                PlayerInitialCardTestData(
                    name = "김영태",
                    dealer = Dealer.withFullDeck()
                )
            )
        ) { (name, dealer) ->
            val player = Player(name = name, dealer = dealer)

            player.name shouldBe name
            player.getCardList().count() shouldBe BlackjackRule.initialCard
        }
    }
})
