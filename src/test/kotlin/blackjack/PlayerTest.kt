package blackjack

import blackjack.domain.Card
import blackjack.domain.Player
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

data class PlayerInitialCardTestData(
    val name: String,
    val cardList: List<Card>,
)

class PlayerTest : FunSpec({
    context("Player는 이름을 가진다") {
        withData(
            listOf(
                "김영태",
                "기임영태"
            )
        ) { name ->
            val player = Player(name)

            player.name shouldBe name
        }
    }
})
